package com.example.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDto {
	
	private List<ReplyVo> list;
	private int replyCnt;
	
}
