package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.user.di.annot.service.UserService;

@ExtendWith(SpringExtension.class) 
@ContextConfiguration(locations = "classpath:mylab-user-di.xml") // 설정 파일 위치 지정
public class UserServiceTest {

    @Autowired
    private UserService userService; 

    @Test
    public void testUserService() {
        assertNotNull(userService, "UserService가 주입되지 않았습니다.");

        assertNotNull(userService.getUserRepository(), "UserRepository가 주입되지 않았습니다.");

        String dbType = userService.getUserRepository().getDbType();
        assertEquals("MySQL", dbType, "DB 타입이 MySQL이 아닙니다.");
     
        assertNotNull(userService.getSecurityService(), "SecurityService가 주입되지 않았습니다.");

        boolean result = userService.registerUser("java_user", "홍길동", "pass1234");
        assertTrue(result, "사용자 등록 기능이 정상적으로 동작하지 않았습니다.");
        
        System.out.println("모든 DI 테스트 및 기능 검증 통과!");
    }
}