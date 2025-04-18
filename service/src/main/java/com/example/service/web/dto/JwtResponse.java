package com.example.service.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

/**
 * Класс dto используется для возвращения токена в теле ответа.
 */
@Data
@AllArgsConstructor
@Value
@Schema(description = "Получение токена")
public class JwtResponse {
    @Schema(description = "Сгенерированный токен")
    @NotBlank
    String token;
}
