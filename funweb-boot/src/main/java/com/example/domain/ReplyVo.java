package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReplyVo {

	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
}
