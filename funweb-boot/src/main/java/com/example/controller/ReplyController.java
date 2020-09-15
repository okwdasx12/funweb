package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Criteria;
import com.example.domain.ReplyPageDto;
import com.example.domain.ReplyVo;
import com.example.service.ReplyService;

import lombok.extern.java.Log;

/*
 REST 컨트롤러의 HTTP method 매핑 방식
 C create	- POST 방식
 R read		- GET 방식
 U update	- PUT 도는 PATCH 방식
 D delete 	- DELETE 방식
 Get방식 - select
 */

@RestController
@RequestMapping("/replies/*")
@Log
public class ReplyController { //이 클래스의 모든 메소드의 리턴값이 JSON또는 XML 응답으로 동작함
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVo replyVo) {
		System.out.println("replyVo : " + replyVo);
		int count = replyService.register(replyVo);
		System.out.println("reply INSERT count : " + count);
		ResponseEntity<String> entity =  null;
		if(count>0) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}//create()
	
	//
	@GetMapping(value="/pages/{bno}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVo>> getList(@PathVariable("bno") int bno) {
		
		List<ReplyVo> list = replyService.getList(bno);
		
		return new ResponseEntity<List<ReplyVo>>(list, HttpStatus.OK);
	}//getList()
	
	@GetMapping(value="/pages/{bno}/{pageNum}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyPageDto> getListWithPage(@PathVariable("bno") int bno, @PathVariable("pageNum") int pageNum) {
		Criteria cri = new Criteria(pageNum,10);
		
		ReplyPageDto replyPageDto = replyService.getListWithPaging(bno, cri);
		
		return new ResponseEntity<ReplyPageDto>(replyPageDto, HttpStatus.OK);
	}//getListWithPage()
	
	@GetMapping(value="/{rno}",  produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVo> get(@PathVariable("rno") int rno) {
		ReplyVo replyVo = replyService.get(rno);
		
		return new ResponseEntity<ReplyVo>(replyVo, HttpStatus.OK);
	}//get()
	
	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		int count = replyService.remove(rno);
		
		ResponseEntity<String> entity =  null;
		
		return (count > 0)? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}//remove()
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVo replyVo, @PathVariable("rno") int rno) {
		replyVo.setRno(rno);
		
		replyService.modify(replyVo);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}
	
}
