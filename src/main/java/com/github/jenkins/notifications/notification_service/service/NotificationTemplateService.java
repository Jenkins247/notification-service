package com.github.jenkins.notifications.notification_service.service;

import com.github.jenkins.notifications.notification_service.model.NotificationTemplate;

import java.util.List;
import java.util.Optional;

public interface NotificationTemplateService {
    NotificationTemplate createTemplate(NotificationTemplate template);

    Optional<NotificationTemplate> getTemplateById(Long id);

    Optional<NotificationTemplate> getTemplateByName(String name);

    List<NotificationTemplate> getAllTemplates();

    NotificationTemplate updateTemplate(Long id, NotificationTemplate template);

    void deleteTemplate(Long id);
}
