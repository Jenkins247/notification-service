package com.github.jenkins.notifications.notification_service.model;

public enum NotificationType {
    EMAIL("email"),
    SMS("sms"),
    PUSH("push"),
    WEBHOOK("webhook");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
