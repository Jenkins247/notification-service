package com.github.jenkins.notifications.notification_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Кому отправляется уведомление (email, телефон и т.д.)
    @Column(nullable = false)
    private String recipient;

    // Тема письма (актуально для email)
    private String subject;

    // Основной текст уведомления
    @Column(nullable = false, length = 1000)
    private String message;

    // Тип уведомления: EMAIL, SMS, PUSH и т.д.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // Текущий статус уведомления: PENDING, SENT, DELIVERED, FAILED и т.д.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    // Ссылка на шаблон (может быть nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private NotificationTemplate template;

    // Приоритет уведомления
    private Integer priority;

    // Канал доставки (например, smtp-server-1, sms-gateway)
    private String deliveryChannel;

    // Дополнительные данные (например, JSON строка)
    @Column(columnDefinition = "TEXT")
    private String metadata;

    // Время создания уведомления
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Запланированное время отправки (может быть null)
    private LocalDateTime scheduledAt;

    // Фактическое время отправки
    private LocalDateTime sentAt;

    // Время доставки получателю
    private LocalDateTime deliveredAt;

    // Количество попыток доставки
    private Integer attempts;

    // Описание последней ошибки, если была
    private String lastError;

    // Идентификатор для отслеживания
    private String trackingId;

    // Перед сохранением — автоматически проставлять дату создания
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = NotificationStatus.PENDING;
        }
        if (this.attempts == null) {
            this.attempts = 0;
        }
    }
}
