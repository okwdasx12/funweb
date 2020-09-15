package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.Criteria;
import com.example.domain.ReplyVo;

public interface ReplyMapper{

	@Insert("INSERT INTO reply (bno, reply, replyer) "
	+ "VALUES (#{bno}, #{reply}, #{replyer})")
	int insert(ReplyVo replyVo);
	
	@Select("SELECT * from reply where rno = #{rno}")
	ReplyVo read(int rno);
	
	@Delete("delete from reply where rno = #{rno}")
	int delete(int rno);
	
	@Update("update reply "
			+ "set reply = #{reply}, update_date = CURRENT_TIMESTAMP "
			+ "where rno = #{rno}")
	void update(ReplyVo replyVo);
	
	@Select("select count(rno) from reply where bno = #{bno}")
	int getCountByBno(int bno);
	
	@Select("select * from reply where bno = #{bno} order by rno desc")
	List<ReplyVo>getList(int bno);
	
	@Select("select * from reply where bno = #{bno} order by rno desc limit #{cri.startRow}, #{cri.amount}")
	List<ReplyVo>getListWithPaging(@Param("bno") int bno, @Param("cri") Criteria cri);
}
