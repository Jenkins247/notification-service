package com.github.jenkins.notifications.notification_service.service;

import com.github.jenkins.notifications.notification_service.dto.request.NotificationRequestDto;
import com.github.jenkins.notifications.notification_service.dto.response.NotificationResponseDto;
import com.github.jenkins.notifications.notification_service.exception.NotificationNotFoundException;
import com.github.jenkins.notifications.notification_service.model.Notification;
import com.github.jenkins.notifications.notification_service.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository INotificationRepository) {
        this.notificationRepository = INotificationRepository;
    }

    @Override
    public Page<NotificationResponseDto> getAllNotifications(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return notificationRepository.findAll(pageable).map(this::mapToResponseDto);
    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new NotificationNotFoundException("Notification not found with id: " + id)
        );
        return mapToResponseDto(notification);
    }

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto request) {
        Notification notification = mapToEntity(request);
        Notification saved = notificationRepository.save(notification);
        return mapToResponseDto(saved);
    }

    @Override
    public NotificationResponseDto updateNotification(Long id, NotificationRequestDto request) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new NotificationNotFoundException("Notification not found with id: " + id)
        );
        notification.setRecipient(request.getRecipient());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setScheduledAt(request.getScheduledAt());
        Notification updated = notificationRepository.save(notification);
        return mapToResponseDto(updated);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new NotificationNotFoundException("Notification not found with id: " + id)
        );
        notificationRepository.delete(notification);
    }

    // Преобразование сущности в DTO
    private NotificationResponseDto mapToResponseDto(Notification n) {
        return NotificationResponseDto.builder()
                .id(n.getId())
                .recipient(n.getRecipient())
                .subject(n.getSubject())
                .message(n.getMessage())
                .type(n.getType())
                .status(n.getStatus())
                .templateId(n.getTemplate().getId())
                .templateName(n.getTemplate().getName())
                .priority(n.getPriority())
                .deliveryChannel(n.getDeliveryChannel())
                .metadata(n.getMetadata())
                .createdAt(n.getCreatedAt())
                .scheduledAt(n.getScheduledAt())
                .sentAt(n.getSentAt())
                .deliveredAt(n.getDeliveredAt())
                .attempts(n.getAttempts())
                .lastError(n.getLastError())
                .trackingId(n.getTrackingId())
                .build();
    }

    private Notification mapToEntity(NotificationRequestDto r) {
        Notification n = new Notification();
        n.setRecipient(r.getRecipient());
        n.setMessage(r.getMessage());
        n.setType(r.getType());
        n.setScheduledAt(r.getScheduledAt());
        // установка других полей по умолчанию или логика init
        return n;
    }
}
