package com.example.service.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Data
@Jacksonized
@Schema(description = "Пользователь")
public class UserDto {
    @Schema(description = "Идентификатор пользователя")
    @NotBlank
    Long id;
    @Schema(description = "Имя пользователя")
    @NotBlank
    String name;
    @Schema(description = "Электронная почта пользователя", example = "mail@mail.com")
    @Email
    @NotBlank
    String username;
}
