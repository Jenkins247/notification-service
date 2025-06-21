package com.github.jenkins.notifications.notification_service.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jenkins.notifications.notification_service.model.NotificationType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {

    @NotNull(message = "Поле recipient не может быть null")
    @Email(message = "Неверный формат email")
    private String recipient;

    @NotBlank(message = "Поле message не может быть пустым")
    @Size(max = 1000, message = "Сообщение не должно превышать 1000 символов")
    private String message;

    @NotNull(message = "Поле type не может быть null")
    private NotificationType type;

    @FutureOrPresent(message = "scheduledAt должно быть сейчас или в будущем")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scheduledAt;
}
