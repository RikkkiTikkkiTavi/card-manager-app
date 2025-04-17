package com.example.card_manager_app.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Класс dto используется для регистрации нового пользователя в системе.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "Регистрация нового пользователя")
public class RegistrationDto {
    @Schema(description = "Имя пользователя")
    @NotBlank
    private String name;

    @Schema(description = "Электронная почта пользователя", example = "mail@mail.com")
    @NotBlank
    @Email
    private String username;

    @Schema(description = "Пароль должен содержать от 6 до 32 символов", example = "password")
    @Size(min = 6, max = 32)
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?^&])[A-Za-z\\d@$!%*#?^&]{3,}$")
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @Schema(description = "Пароль должен содержать от 6 до 32 символов", example = "password")
    @Size(min = 6, max = 32)
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?^&])[A-Za-z\\d@$!%*#?^&]{3,}$")
    @JsonProperty(access = WRITE_ONLY)
    private String confirmPassword;
}
