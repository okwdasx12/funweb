package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.AttachfileVo;

public interface AttachfileMapper {
	
	@Select("SELECT * FROM attachfile WHERE uuid = #{uuid}")
	AttachfileVo getAttachfileByUuid(String uuid);

	@Select("SELECT * FROM attachfile WHERE bno = #{bno}")
	List<AttachfileVo> getAttachfilesByBno(int bno);
	
	@Insert("INSERT INTO attachfile (uuid, filename, uploadpath, image, bno) "
			+ "VALUES (#{uuid}, #{filename}, #{uploadpath}, #{image}, #{bno})")
	void insert(AttachfileVo vo);
	
	// 게시글 삭제용
	@Delete("DELETE FROM attachfile WHERE bno = #{bno}")
	void deleteAttachfilesByBno(int bno);
	
	// 게시글 수정용
	@Delete("DELETE FROM attachfile WHERE uuid = #{uuid}")
	void deleteAttachfileByUuid(String uuid);
	
	
	// 파라미터 타입이 컬렉션일때는 @Param 적용해야함
	void deleteAttachfilesByUuids(@Param("uuids") List<String> uuids);
	
}










