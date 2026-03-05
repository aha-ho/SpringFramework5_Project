package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService service;

    @Test
    public void testShoppingCart() {
        // 1. 객체 생성 여부 검증
        assertNotNull(cart, "ShoppingCart 빈이 주입되지 않았습니다.");

        // 2. 리스트 사이즈 검증
        assertEquals(2, cart.getProducts().size(), "상품 개수가 일치하지 않습니다.");

        // 3. 첫 번째 상품 이름 검증
        assertEquals("노트북", cart.getProducts().get(0).getName());

        // 4. 두 번째 상품 이름 검증
        assertEquals("스마트폰", cart.getProducts().get(1).getName());
        
        System.out.println("ShoppingCart 테스트 통과: " + cart);
    }

    @Test
    public void testOrderService() {
        // OrderService 주입 여부 확인 및 계산 결과 검증
        assertNotNull(service);
        
        double expectedTotal = 150000 + 800000;
        assertEquals(expectedTotal, service.calculateOrderTotal());
        
        System.out.println("OrderService 테스트 통과! 총액: " + service.calculateOrderTotal());
    }
}