package com.example.domain;

import lombok.Data;

//VO (Value Object) : 데이터베이스의 특정 테이블에 매핑 용도
//DTO(Data Transfer Object) : 데이터베이스와 무관하게 데이터 전송 용도 
@Data
public class PageDto {

		private int totalCount;
		private int pageCount;
		private int pageBlock;
		private int startPage;
		private int endPage;
		private String category;
		private String search;
}
