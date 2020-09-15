package com.example.domain;

import lombok.Data;

@Data
public class MailDto {
	private String address;//받는사람 이메일 주소
	private String title; //이메일 제목
	private String message; //이메일내용
	
}
