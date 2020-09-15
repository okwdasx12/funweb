package com.example;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class JapaneseChef implements Chef {

	@Override
	public void doCook() {
		// TODO Auto-generated method stub
		System.out.println("일본음식을 요리합니다.");
	}

}
