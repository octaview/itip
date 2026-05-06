package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.NotificationDto;
import org.example.model.entity.Notification;
import org.example.model.entity.User;
import org.example.model.enums.NotificationChannel;
import org.example.model.enums.NotificationStatus;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Notification createNotification(NotificationDto request) {
        log.info("начинаем создание уведомления для получателя с id: {}", request.getRecipientId());
        
        // если пользователь не найден, выбросится исключение
        User user = userRepository.findById(request.getRecipientId()).orElseThrow(() -> {
            log.error("ошибка: получатель с id {} не существует", request.getRecipientId());
            return new RuntimeException("получатель не найден");
        });

        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());
        notification.setStatus(NotificationStatus.CREATED);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRecipient(user);

        Notification saved = notificationRepository.save(notification);
        log.info("уведомление успешно создано, id: {}", saved.getId());
        return saved;
    }

    public List<Notification> getAllNotifications() {
        log.debug("достаем список всех уведомлений");
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> {
            log.warn("уведомление с id {} не найдено", id);
            return new RuntimeException("уведомление не найдено");
        });
    }

    @Transactional
    public Notification updateNotification(Long id, NotificationDto request) {
        log.info("начинаем обновление уведомления с id: {}", id);
        Notification notification = getNotificationById(id);
        
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());

        if (request.getStatus() == NotificationStatus.SENT && notification.getStatus() != NotificationStatus.SENT) {
            notification.setSentAt(LocalDateTime.now());
            log.debug("установлен статус sent, время отправки зафиксировано");
        }
        
        if (request.getStatus() != null) {
            notification.setStatus(request.getStatus());
        }

        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteNotification(Long id) {
        log.info("удаляем уведомление с id: {}", id);
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }

    // --- методы из спринг дата ---

    public List<Notification> getNotificationsByStatus(NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

    public List<Notification> getNotificationsByChannel(NotificationChannel channel) {
        return notificationRepository.findByChannel(channel);
    }

    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }
    
    public List<Notification> getNotificationsByStatusAndChannel(NotificationStatus status, NotificationChannel channel) {
        return notificationRepository.findByStatusAndChannel(status, channel);
    }
    
    public List<Notification> getRecentNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public List<Notification> getCustomNotifications(Long recipientId, NotificationStatus status) {
        return notificationRepository.findByRecipientIdAndStatusCustom(recipientId, status);
    }
}