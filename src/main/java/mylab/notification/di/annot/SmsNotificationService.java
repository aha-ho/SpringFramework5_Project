package mylab.notification.di.annot;

import mylab.notification.di.annot.NotificationService;

public class SmsNotificationService implements NotificationService {
    private String provider;

    public SmsNotificationService() {
        this.provider = "SKT"; 
    }

    public SmsNotificationService(String provider) {
        this.provider = provider;
    }

    public String getProvider() { 
        return provider; 
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS 알림 전송: " + message + " (제공업체: " + provider + ")");
    }
}