package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVo;

public interface BoardMapper {
	
	@Select("select ifnull(max(num), 0) + 1 as max_num from board ")
	int getBoardNum();
	
	
	int insertBoard(BoardVo vo);
	
	
	int getTotalCount(@Param("category") String category, @Param("search") String search);
	
	// 매퍼 메소드의 매개변수가 2개 이상일때는
	// @Param 애노테이션 값으로 sql문에 배치함.
	// select문을 xml에서 실행할때는
	// resultType 속성을 반드시 해당 Vo로 지정해야 함.
	List<BoardVo> getBoards(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("category") String category, @Param("search") String search);
	
	
	BoardVo getBoardByNum(int num);
	
	
	void updateReadcount(int num);
	
	
	void update(BoardVo vo);
	
	@Delete("DELETE FROM board WHERE num = #{num}")
	void deleteByNum(int num);
	
	
	void updateReplycnt(@Param("bno")int bno, @Param("amount")int amount);
	
	@Delete("delete from board")
	void deleteAll();
	
	@Select("SELECT COUNT(*) FROM board")
	int getCount();
	
	int updateReSeqByReRef(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
	
	
	// board와 attachfile 테이블 조인해서 select
	BoardVo getBoardAndAttachfilesByNum(int num);
	
	
}





