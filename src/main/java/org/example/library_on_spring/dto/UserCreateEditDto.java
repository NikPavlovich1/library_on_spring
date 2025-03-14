package org.example.library_on_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.library_on_spring.validation.UserInfo;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateEditDto {

    @Schema(description = "Имя пользователя", example = "Иван", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 50, message = "Длина имени должна быть не менее 2 и не более 50 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-]+$", message = "Имя должно состоять только из букв")
    String firstname;

    @Schema(description = "Фамилия пользователя", example = "Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 50, message = "Длина фамилии должна быть не менее 2 и не более 50 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Фамилия должна состоять только из букв")
    String lastname;

    String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    LocalDate createdAt;

    Long bookId;
}
