package com.github.jenkins.notifications.notification_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_templates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название шаблона (например, "Order Confirmation")
    @Column(nullable = false, unique = true)
    private String name;

    // Тип уведомления, для которого используется шаблон (EMAIL, SMS, PUSH)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // Тема сообщения (актуально для email)
    private String subject;

    // Текст шаблона с переменными (например, "Здравствуйте, {username}!")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    // Язык шаблона (например, "ru", "en")
    private String language;

    // Время создания шаблона
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Время последнего обновления
    private LocalDateTime updatedAt;

    // Версия шаблона (для контроля изменений)
    private Integer version;

    // Автоматически устанавливать даты при создании/обновлении
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.version = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.version == null) {
            this.version = 1;
        } else {
            this.version++;
        }
    }
}
