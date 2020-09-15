package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * @author it
 *
 */
@Data
public class BoardVo {
	
	//JOIN 쿼리로 첨부파일정보를 리스트 필드에 설정 용도
	private List<AttachfileVo> attachList;

	private Integer num;
	private String name;
	private String passwd;
	private String subject;
	private String content;
	private Integer readcount;

	private String ip;
	private LocalDateTime regDate;

	private Integer reRef; /* 글 그룹 번호 */
	private Integer reLev; /* 글 들여쓰기 레벨 */
	private Integer reSeq; /* 글 그룹 내에서의 순서 */
	
	private int replycnt; //댓글 갯수


}
