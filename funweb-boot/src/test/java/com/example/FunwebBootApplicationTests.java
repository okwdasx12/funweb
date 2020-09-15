package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.service.SampleService;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class FunwebBootApplicationTests {

	@Autowired
	private Restaurant restaurant;
	
	@Autowired
	private SampleService sampleService;
	
	@Test
	void testSampleServiceClass() {
		log.info(sampleService.getClass().getName());
		System.out.println(sampleService);
	}
	
	@Test
	void testAdd() {
		int result = sampleService.doAdd("123","456");
		log.info("결과 : " + result);
		assertEquals(579, result);
	}
	
	@Test
	void testAddError() {
		
		//예외객체 타입검사
		
		Throwable exception = assertThrows(NumberFormatException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				sampleService.doAdd("123", "ABC");
			}
		});
		
	}
	
	@Disabled
	@Test
	void contextLoads() {
	}
	
	@Test
	void testRestaurant() {
		log.info("@Test - testRestaurant() 호출됨");
		
		//스프링 프레임워크를 사용하지 않고 Restaurant를 사용하는 경우
		//Restaurant이 Check를 직접 생성해서 사용함.
//		Restaurant restaurant = new Restaurant();
////		Chef chef = new ChineseChef();
//		Chef chef = new JapaneseChef();
//		
//		//객체간의 의존관계 주입 (Dependency Injection, DI)
//		restaurant.setChef(chef);
//		
		restaurant.makeDish();
	}
}
