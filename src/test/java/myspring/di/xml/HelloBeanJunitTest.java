package myspring.di.xml;

//static import
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	ApplicationContext context;
	
	@BeforeEach
	void init() {
		//Container 객체생성
		context = new GenericXmlApplicationContext("classpath:hello-bean.xml");		
	}
	//Constructor Injection을 테스트하는 메소드
	@Test
	void helloBeanCons() {
		Hello hello = context.getBean("helloC", Hello.class);
		assertEquals("Hello 생성자", hello.sayHello());
		hello.print();
	}
	
	//SetterInjection을 테스트하는 메소드
	@Test @Disabled
	void helloBeanSetter() {
		Hello helloById = (Hello)context.getBean("hello");
		Hello helloByType = context.getBean("hello", Hello.class);
		
		//Hello SpringBean이 Singleton 객체인지를 검증하기
		System.out.println(helloById == helloByType);
		
		assertSame(helloById, helloByType);
		
		assertEquals("Hello 스프링", helloByType.sayHello());
		
		helloByType.print();
		
		Printer strPrinter = context.getBean("strPrinter", Printer.class);
		
		assertEquals("Hello 스프링", strPrinter.toString());
	}
}