package org.example.library_on_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library_on_spring.database.entity.Role;
import org.example.library_on_spring.validation.UniqueUserName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UniqueUserName
public class RegisterAdminRequest {

    @Schema(description = "Имя пользователя",
            example = "Длина имени должна быть не менее 2 и не более 50 символов. Имя должно состоять только из букв",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 50, message = "Длина имени пользователя должна быть не менее 2 и не более 50 символов")
    @NotBlank
    private String username;

    @Schema(description = "Пароль",
            example = "Длина пароля должна быть не менее 2 и не более 100 символов. Пароль может содержать не только буквы, но также цифры и специальные символы",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 100, message = "Длина пароля должна быть не менее 2 и не более 100 символов")
    @Pattern(regexp = "^(?=.*[a-zA-Zа-яА-ЯёЁ])(?=.*\\d)[a-zA-Zа-яА-ЯёЁ0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~`]{3,32}$",
            message = "Пароль должен содержать 3-32 символа: буквы (EN/RU), цифры и спецсимволы без пробелов.")
    @NotBlank
    private String password;

    @Schema(description = "Роль пользователя", example = "ADMIN")
    @NotBlank(message = "Роль не может быть null")
    private Role role;

    private Long userId;
}
