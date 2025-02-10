package org.example.library_on_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    String firstname;

    String lastname;

    String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate createdAt;

    Long bookId;
}
