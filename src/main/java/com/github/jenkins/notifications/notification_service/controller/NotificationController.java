package com.github.jenkins.notifications.notification_service.controller;

import com.github.jenkins.notifications.notification_service.dto.request.NotificationRequestDto;
import com.github.jenkins.notifications.notification_service.dto.response.NotificationResponseDto;
import com.github.jenkins.notifications.notification_service.service.NotificationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class NotificationController {

    private final NotificationServiceImpl notificationServiceImpl;

    public NotificationController(NotificationServiceImpl notificationServiceImpl) {
        this.notificationServiceImpl = notificationServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationResponseDto>> getAllNotification(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ){
        Page<NotificationResponseDto> notifications = notificationServiceImpl.getAllNotifications(page, size, sortBy, sortDir);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<NotificationResponseDto> createNotification(
            @RequestBody @Valid NotificationRequestDto request) {

        NotificationResponseDto createdNotification =
                notificationServiceImpl.createNotification(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdNotification.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdNotification);
    }

}
