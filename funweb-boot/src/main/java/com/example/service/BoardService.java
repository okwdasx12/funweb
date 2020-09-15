package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AttachfileVo;
import com.example.domain.BoardVo;
import com.example.mapper.AttachfileMapper;
import com.example.mapper.BoardMapper;

@Service
@Transactional // 모든 메소드 각각이 한개의 트랜잭션 단위로 수행됨
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachfileMapper attachfileMapper;

	
	
	
	// 게시판 새글번호 생성해서 가져오기
		public int getBoardNum() {
			return boardMapper.getBoardNum();
		} // getBoardNum()
		
		
		// 주글 insert 메소드
		public void insert(BoardVo vo) {
				// 새글번호 구하기
				int num = boardMapper.getBoardNum();
				// 새글번호를 vo에 설정
				vo.setNum(num);
				// 주글관련 re 필드값 설정
				vo.setReRef(num);
				vo.setReLev(0);
				vo.setReSeq(0);
				//조회수 0
				vo.setReadcount(0);
				
				boardMapper.insertBoard(vo);
		}//insert()
		
		public void insertBoardAndAttaches(BoardVo boardVo, List<AttachfileVo> attachList) {
			//게시글 등록
			boardMapper.insertBoard(boardVo);
			
			//첨부파일이 있으면 등록
			if(attachList.size()>0) {
				for(AttachfileVo attachVo : attachList) {
					attachfileMapper.insert(attachVo);
				}
			}
		}
		
			
		// board 테이블의 전체 행 갯수 가져오기
		public int getTotalCount(String category, String search) {
			int count = boardMapper.getTotalCount(category, search);
			return count;
		} // getTotalCount()
		
		
		// 게시판 글목록 가져오기
		public List<BoardVo> getBoards(int startRow, int pageSize, String category, String search) {
			List<BoardVo> list = boardMapper.getBoards(startRow, pageSize, category, search);
			return list;
		} // getBoards()
		
		
		public BoardVo getBoardByNum(int num) {
			BoardVo vo = boardMapper.getBoardByNum(num);
			return vo;
		} // getBoardByNum()
		
		
		
		//board 테이블과 attachfile테이블 조인해서 결과 리턴
		public BoardVo getBoardAndAttachfilesByNum(int num){
			BoardVo boardVo = boardMapper.getBoardAndAttachfilesByNum(num);
			return boardVo;
		}//getBoardAndAttachfilesByNum
		
		
		// 특정글 조회수 1증가시키는 메소드
		public void updateReadcount(int num) {
			boardMapper.updateReadcount(num);
		} // updateReadcount()
		
		
		public void update(BoardVo vo) {
			boardMapper.update(vo);
		} // update()
		
		
		public void deleteByNum(int num) {
			boardMapper.deleteByNum(num);
		} // deleteByNum()
		
		
		// 답글쓰기
		public void replyInsert(BoardVo vo) {
			boardMapper.updateReSeqByReRef(vo.getReRef(), vo.getReSeq());
			//새글번호 구하기
			int num = boardMapper.getBoardNum();
				
			//답글 insert할 정보로 vo를 설정
			vo.setNum(num);
			vo.setReLev(vo.getReLev()+1);
			vo.setReSeq(vo.getReSeq()+1);
			vo.setReadcount(0); //답글 조회수 0
			
			boardMapper.insertBoard(vo);
		} // replyInsert()
		
		
		// 샘플 주글들을 insert하기
		public void insertDummyRows(int count) {
			// BoardVo 객체를 100개 준비하기
			for (int i=1; i<=count; i++) {
				BoardVo vo = new BoardVo();
				
				int num = getBoardNum(); // 새글번호 가져오기
				
				vo.setNum(num); // 기본키 항목
				vo.setName("홍길동"+num);
				vo.setPasswd("1234");
				vo.setSubject("글제목" + num);
				vo.setContent("글내용\n입니다. " + num);
				vo.setReadcount(0);
				vo.setRegDate(LocalDateTime.now());
				vo.setReRef(num);
				vo.setReLev(0);
				vo.setReSeq(0);
				
				insert(vo); // dao객체의 insert() 활용해서 행 추가
			} // for
		} // insertDummyRow
		
		public void deleteAll() {
				boardMapper.deleteAll();
		}
		
		public int getCount() {
			int count = boardMapper.getCount();
			return count;
		}
}	
