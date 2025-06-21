package com.github.jenkins.notifications.notification_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jenkins.notifications.notification_service.model.NotificationStatus;
import com.github.jenkins.notifications.notification_service.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("recipient")
    private String recipient;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("message")
    private String message;

    @JsonProperty("type")
    private NotificationType type;

    @JsonProperty("status")
    private NotificationStatus status;

    @JsonProperty("templateId")
    private Long templateId;

    @JsonProperty("templateName")
    private String templateName;

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("deliveryChannel")
    private String deliveryChannel;

    @JsonProperty("metadata")
    private String metadata;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("scheduledAt")
    private LocalDateTime scheduledAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("sentAt")
    private LocalDateTime sentAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("deliveredAt")
    private LocalDateTime deliveredAt;

    @JsonProperty("attempts")
    private Integer attempts;

    @JsonProperty("lastError")
    private String lastError;

    @JsonProperty("trackingId")
    private String trackingId;
}
