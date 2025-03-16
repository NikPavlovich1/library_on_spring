package org.example.library_on_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.library_on_spring.validation.UniqueUserName;
import org.example.library_on_spring.validation.UserInfo;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
@UniqueUserName
public class UserCreateEditDto {

    @Schema(description = "Имя пользователя", example = "Длина имени должна быть не менее 2 и не более 50 символов. Имя должно состоять только из букв", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 50, message = "Длина имени должна быть не менее 2 и не более 50 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-]+$", message = "Имя должно состоять только из букв")
    String firstname;

    @Schema(description = "Фамилия пользователя", example = "Длина фамилии должна быть не менее 2 и не более 50 символов. Фамилия должна состоять только из букв", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 50, message = "Длина фамилии должна быть не менее 2 и не более 50 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Фамилия должна состоять только из букв")
    String lastname;

    @Schema(description = "Отчество пользователя", example = "Отчество пользователя не обязательно, оно может оставаться пустым")
    String surname;

    @Schema(description = "Дата создания пользователя", example = "2023-10-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    LocalDate createdAt;

    @Schema(description = "ID книги", example = "1")
    Long bookId;
}
