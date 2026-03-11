package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class) // 자바 설정 클래스 지정
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager; // a. NotificationManager 주입 받기

    @Test
    public void testNotificationManager() {
        // b. NotificationManager의 레퍼런스가 Not Null 인지 검증
        assertNotNull(notificationManager, "NotificationManager 주입 실패");

        // c. 이메일 서비스 검증
        // getEmailService()가 인터페이스 타입을 반환한다면 구현체로 형변환하여 상세 값 검증
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertNotNull(emailService, "EmailNotificationService 주입 실패");
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // d. SMS 서비스 검증
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertNotNull(smsService, "SmsNotificationService 주입 실패");
        assertEquals("SKT", smsService.getProvider());

        // e. NotificationManager의 메서드 실행
        System.out.println("==== 알림 발송 테스트 시작 ====");
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
        System.out.println("==== 알림 발송 테스트 종료 ====");
    }
}