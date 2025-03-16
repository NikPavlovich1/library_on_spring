package org.example.library_on_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.library_on_spring.validation.UniqueBook;

@FieldNameConstants
@UniqueBook
public record BookCreateEditDto(

        @Schema(description = "Название книги", example = "Длина названия должна быть не менее 2 и не более 100 символов. Название книги может содержать не только буквы, но также цифры и пробелы", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(min = 2, max = 100, message = "Длина названия должна быть не менее 2 и не более 100 символов")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s-'.,]+$", message = "Название книги может содержать не только буквы, но также цифры и пробелы")
        String title,

        @Schema(description = "Имя автора", example = "Длина имени автора должна быть не менее 2 и не более 100 символов. Имя автора может содержать не только буквы, но и специальные символы (- ' . ,)", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(min = 2, max = 100, message = "Длина имени автора должна быть не менее 2 и не более 100 символов")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-'.,]+$", message = "Имя автора может содержать не только буквы, но и специальные символы (- ' . ,)")
        String author,

        @Schema(description = "Категория книги", example = "NP", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Pattern(regexp = "^(B|NP|O|P|Ps|Pr)$", message = "Категория должна быть одной из: B, NP, O, P, Ps, Pr")
        String category,

        @Schema(description = "Порядковый номер книги в категории", example = "Порядковый номер книги должен содержать цифры от 1 до 9", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Pattern(regexp = "^[1-9]$", message = "Порядковый номер книги должен содержать цифры от 1 до 9")
        Integer categoryOrder
    ) {
}
