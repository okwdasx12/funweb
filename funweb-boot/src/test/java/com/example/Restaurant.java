package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 Spring Web MVC 모듈에서
 @Component를 상속받는 애노테이션 종류
 - @controller	컨트롤러 역할을 하는 클래스에 사용함
 - @Service		트랜잭션 처리를 수행하느느 클래스에 사용함
 - @Repository (저장소) DAO클래스에 사용함
 */

@Component
public class Restaurant {
	@Autowired
	private Chef chef; //autowired로  setter 포함됨

	public void makeDish(){
		chef.doCook();
	}
}
