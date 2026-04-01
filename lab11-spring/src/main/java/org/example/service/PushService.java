package org.example.service;

public class PushService implements AdvancedMessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("PUSH to " + recipient + ": " + message);
    }

    @Override
    public String getServiceType() {
        return "PUSH_NOTIFICATION";
    }
}