package org.example.library_on_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BookCreateEditDto(

        @Schema(description = "Название книги", example = "Война и мир", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(min = 2, max = 100, message = "Длина названия должна быть не менее 2 и не более 100 символов")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s-'.,]+$", message = "Название книги может содержать не только буквы, но также цифры и пробелы")
        String title,

        @Schema(description = "Имя автора", example = "Л.Н. Толстой", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(min = 2, max = 100, message = "Длина имени автора должна быть не менее 2 и не более 100 символов")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-'.,]+$", message = "Имя автора может содержать не только буквы, но и специальные символы (- ' . ,)")
        String author,

        @Schema(description = "Категория книги", example = "NP", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Pattern(regexp = "^(B|NP|O|P|Ps|Pr)$", message = "Категория должна быть одной из: B, NP, O, P, Ps, Pr")
        String category,

        Integer categoryOrder
    ) {
}
