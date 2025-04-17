package com.example.card_manager_app.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Класс dto используется при запросе на аутентификацию пользователя.
 */
@Data
@AllArgsConstructor
@Schema(description = "Запрос на получение токена")
public class JwtRequest {

    @Schema(description = "Электронная почта пользователя", example = "mail@mail.com")
    @Email
    @NotBlank
    String email;

    @Schema(description = "Пароль должен содержать от 6 до 32 символов", example = "password")
    @Size(min = 6, max = 32)
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?^&])[A-Za-z\\d@$!%*#?^&]{3,}$")
    @JsonProperty(access = WRITE_ONLY)
    String password;
}
