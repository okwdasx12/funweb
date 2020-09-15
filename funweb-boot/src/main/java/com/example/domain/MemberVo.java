package com.example.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//member 테이블의 레코드(행) 한개를 표현하는 클래스

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo implements Cloneable {
	
	private String id ;
	private String passwd;
	private String name;
	private String email;
	private LocalDateTime regDate;
	private String address;
	private String tel;
	private String mtel;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
