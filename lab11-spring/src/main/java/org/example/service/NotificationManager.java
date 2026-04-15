package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationManager {
    private final Map<String, MessageService> messageServices;
    
    private final MessageService primaryService;
    
    private final MessageService emailService;

    @Autowired
    public NotificationManager(
            Map<String, MessageService> messageServices,
            MessageService primaryService,
            @Qualifier("customEmail") MessageService emailService) {
        
        this.messageServices = messageServices;
        this.primaryService = primaryService;
        this.emailService = emailService;
    }

    public void notify(String message, String recipient, String type) {
        if (type == null || type.isEmpty() || type.equalsIgnoreCase("primary")) {
            primaryService.sendMessage(message, recipient);
            
        } else if (type.equalsIgnoreCase("email-only")) {
            emailService.sendMessage(message, recipient);
            
        } else {
            MessageService service = messageServices.get(type);
            if (service != null) {
                service.sendMessage(message, recipient);
                
                if (service instanceof AdvancedMessageService advancedService) {
                    System.out.println("Использован продвинутый сервис: " + advancedService.getServiceType());
                }
            } else {
                System.out.println("Сервис '" + type + "' не найден. Доступные сервисы: " + messageServices.keySet());
            }
        }
    }
}