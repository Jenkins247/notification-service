package com.github.jenkins.notifications.notification_service.repository;

import com.github.jenkins.notifications.notification_service.model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {
    NotificationTemplate findByName(String name);
}
