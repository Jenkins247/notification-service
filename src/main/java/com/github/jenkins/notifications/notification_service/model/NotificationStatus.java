package com.github.jenkins.notifications.notification_service.model;

public enum NotificationStatus {
    PENDING("pending"),
    PROCESSING("processing"),
    SENT("sent"),
    DELIVERED("delivered"),
    FAILED("failed"),
    CANCELLED("cancelled");

    private final String value;

    NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
