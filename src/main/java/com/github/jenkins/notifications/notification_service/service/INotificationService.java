package com.github.jenkins.notifications.notification_service.service;

import com.github.jenkins.notifications.notification_service.dto.request.NotificationRequestDto;
import com.github.jenkins.notifications.notification_service.dto.response.NotificationResponseDto;
import org.springframework.data.domain.Page;

public interface INotificationService {
    Page<NotificationResponseDto> getAllNotifications(int page, int size, String sortBy, String sortDir);

    NotificationResponseDto getNotificationById(Long id);

    NotificationResponseDto createNotification(NotificationRequestDto request);

    NotificationResponseDto updateNotification(Long id, NotificationRequestDto request);

    void deleteNotification(Long id);
}
