package com.github.jenkins.notifications.notification_service.service;

import com.github.jenkins.notifications.notification_service.exception.NotificationTemplateNotFoundException;
import com.github.jenkins.notifications.notification_service.model.NotificationTemplate;
import com.github.jenkins.notifications.notification_service.repository.NotificationTemplateRepository;

import java.util.List;
import java.util.Optional;

public class NotificationTemplateServiceImpl implements NotificationTemplateService {
    private final NotificationTemplateRepository templateRepository;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public NotificationTemplate createTemplate(NotificationTemplate template) {
        return templateRepository.save(template);
    }

    @Override
    public Optional<NotificationTemplate> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    @Override
    public Optional<NotificationTemplate> getTemplateByName(String name) {
        return Optional.ofNullable(templateRepository.findByName(name));
    }

    @Override
    public List<NotificationTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public NotificationTemplate updateTemplate(Long id, NotificationTemplate updateTemplate) {
        return templateRepository.findById(id).map(template -> {
            template.setName(updateTemplate.getName());
            template.setType(updateTemplate.getType());
            template.setSubject(updateTemplate.getSubject());
            template.setBody(updateTemplate.getBody());
            template.setLanguage(updateTemplate.getLanguage());
            return templateRepository.save(template);
        }).orElseThrow(() -> new NotificationTemplateNotFoundException("Template not found with id: " + id));
    }

    @Override
    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}
