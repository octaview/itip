package org.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "имя не должно быть пустым")
    @Size(max = 100, message = "имя не должно быть длиннее 100 символов")
    private String name;

    @NotBlank(message = "email обязателен")
    @Email(message = "некорректный формат email")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "email не соответствует требуемому шаблону"
    )
    private String email;

    @Pattern(
            regexp = "^\\+?[1-9]\\d{1,14}$",
            message = "некорректный формат телефона (ожидается международный формат, например +79990001122)"
    )
    private String phone;

    private String deviceToken;
    private String telegramChatId;
    private LocalDateTime createdAt;
}