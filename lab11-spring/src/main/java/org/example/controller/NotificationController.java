package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.NotificationMapper;
import org.example.model.dto.NotificationDto;
import org.example.model.entity.Notification;
import org.example.model.enums.NotificationChannel;
import org.example.model.enums.NotificationStatus;
import org.example.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @PostMapping("/add")
    public NotificationDto createNotification(@RequestBody @Valid NotificationDto request) {
        log.info("поступил http-запрос на создание уведомления");
        Notification response = notificationService.createNotification(request);
        return notificationMapper.toDto(response);
    }

    @GetMapping("/all")
    public List<NotificationDto> getAllNotifications() {
        log.debug("поступил http-запрос на получение всех уведомлений");
        return notificationService.getAllNotifications().stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable Long id) {
        log.debug("поступил http-запрос на получение уведомления по id: {}", id);
        Notification response = notificationService.getNotificationById(id);
        return notificationMapper.toDto(response);
    }

    @PutMapping("/{id}")
    public NotificationDto updateNotification(@PathVariable Long id, @RequestBody @Valid NotificationDto request) {
        log.info("поступил http-запрос на обновление уведомления по id: {}", id);
        Notification response = notificationService.updateNotification(id, request);
        return notificationMapper.toDto(response);
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable Long id) {
        log.info("поступил http-запрос на удаление уведомления по id: {}", id);
        notificationService.deleteNotification(id);
        return "уведомление удалено";
    }

    @GetMapping("/status/{status}")
    public List<NotificationDto> getByStatus(@PathVariable NotificationStatus status) {
        return notificationService.getNotificationsByStatus(status).stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @GetMapping("/channel/{channel}")
    public List<NotificationDto> getByChannel(@PathVariable NotificationChannel channel) {
        return notificationService.getNotificationsByChannel(channel).stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @GetMapping("/recipient/{recipientId}")
    public List<NotificationDto> getByRecipientId(@PathVariable Long recipientId) {
        return notificationService.getNotificationsByRecipientId(recipientId).stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @GetMapping("/recent")
    public List<NotificationDto> getRecent() {
        return notificationService.getRecentNotifications().stream()
                .map(notificationMapper::toDto)
                .toList();
    }
    
    @GetMapping("/custom")
    public List<NotificationDto> getCustom(
            @RequestParam Long recipientId, 
            @RequestParam NotificationStatus status) {
        return notificationService.getCustomNotifications(recipientId, status).stream()
                .map(notificationMapper::toDto)
                .toList();
    }
}