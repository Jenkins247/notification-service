package com.github.jenkins.notifications.notification_service.exception;

public class NotificationTemplateNotFoundException extends RuntimeException {
    public NotificationTemplateNotFoundException(String message) {
        super(message);
    }

    public NotificationTemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
