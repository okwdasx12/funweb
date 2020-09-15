package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.example.domain.MemberVo;

public interface MemberMapper {
	//회원가입
	//애노테이션이 없으면 같은 경로의 같은 이름의
	//xml 매퍼파일을 찾아서 해당sql구문을 실행함.
	//@Insert("INSERT INTO member(id, passwd, name, email, reg_date, address, tel, mtel) "
	//+ "VALUES (#{id}, #{passwd}, #{name}, #{email}, #{regDate}, #{address}, #{tel}, #{mtel})") //같은 경로의 xml로 이동
	int insert(MemberVo vo);
	
	@Select("select passwd from member where id = #{id}")
	String getPasswdById(String id);
	
	@Select("SELECT COUNT(*) FROM member WHERE id = #{id}")
	int countMemberById(String id);
	
	@Select("select * from member order by id asc") 
	List<MemberVo> getMembers(); 
	
	@Select("SELECT * FROM member WHERE id = #{id}")
	MemberVo getMemberById(String id);
	
	int update(MemberVo vo);
	
	void testUpdate(MemberVo vo);
	
	@Delete("DELETE FROM member WHERE id = #{id}")
	int deleteById(String id);
	
	@Delete("DELETE FROM member WHERE id = #{id}")
	void testDeleteById(String id);
	
	@Delete("delete from member")
	void deleteAll();
	
	@Select("SELECT COUNT(*) FROM member")
	int getCount();
}
