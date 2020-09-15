package com.example;

import org.springframework.stereotype.Component;

@Component
public class ChineseChef implements Chef {

	@Override
	public void doCook() {
		System.out.println("중국음식을 요리합니다.");
	}

}
