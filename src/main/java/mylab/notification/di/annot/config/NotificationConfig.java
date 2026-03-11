package mylab.notification.di.annot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.SmsNotificationService;
import mylab.notification.di.annot.NotificationManager;

@Configuration
public class NotificationConfig {

    @Bean
    public EmailNotificationService emailNotificationService() {
        // EmailNotificationService 내부에서 기본값(smtp.gmail.com, 587)이 설정되어 있다고 가정합니다.
        return new EmailNotificationService();
    }

    @Bean
    public SmsNotificationService smsNotificationService() {
        // SmsNotificationService 내부에서 기본값(SKT)이 설정되어 있다고 가정합니다.
        return new SmsNotificationService();
    }

    @Bean
    public NotificationManager notificationManager() {
        // @Bean 메서드를 호출하여 의존성을 주입합니다.
        return new NotificationManager(emailNotificationService(), smsNotificationService());
    }
}