package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Criteria;
import com.example.domain.ReplyPageDto;
import com.example.domain.ReplyVo;
import com.example.mapper.BoardMapper;
import com.example.mapper.ReplyMapper;

import lombok.extern.java.Log;

@Service
@Transactional
@Log
public class ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardMapper boardMapper;
	
	public int register(ReplyVo replyVo) {
		log.info("register : " + replyVo);
		
		boardMapper.updateReplycnt(replyVo.getBno(), 1);
		
		int insertCount = replyMapper.insert(replyVo);
		return insertCount;
	}
	
	public ReplyVo get(int rno) {
		ReplyVo replyVo = replyMapper.read(rno);
		return replyVo;
	}
	
	public int remove(int rno) {
		ReplyVo replyVo = replyMapper.read(rno);
		
		boardMapper.updateReplycnt(replyVo.getBno(), -1);
		
		return replyMapper.delete(rno);
	}
	
	public void modify(ReplyVo replyVo) {
		replyMapper.update(replyVo);
	}
	
	public int getCountByBno(int bno) {
		int count = replyMapper.getCountByBno(bno);
		return count;
	}
	
	public List<ReplyVo>getList(int bno){
		List<ReplyVo> list= replyMapper.getList(bno);
		return list;
	}
	
	public ReplyPageDto getListWithPaging(int bno, Criteria cri){
		List<ReplyVo> list= replyMapper.getListWithPaging(bno, cri);
		int replyCnt = replyMapper.getCountByBno(bno);
		ReplyPageDto replyPageDto = new ReplyPageDto(list, replyCnt);
		return replyPageDto;
	}
}
