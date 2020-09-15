package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AttachfileVo;
import com.example.mapper.AttachfileMapper;

import lombok.extern.java.Log;

@Service
@Log
@Transactional
public class AttachfileService {
	@Autowired
	private AttachfileMapper attachfileMapper;
	
	public AttachfileVo getAttachfileByUuid(String uuid) {
		AttachfileVo vo = attachfileMapper.getAttachfileByUuid(uuid);
		return vo;
	}
	
	public List<AttachfileVo> getAttachfilesByBno(int bno) {
		List<AttachfileVo> list = attachfileMapper.getAttachfilesByBno(bno);
		return list;
	}
	
	// 첨부파일정보 한개 추가
	public void insert(AttachfileVo vo) {
		attachfileMapper.insert(vo);
	} // insert
	
	
	// 첨부파일정보 여러개 추가
	public void insert(List<AttachfileVo> list) {
		for (AttachfileVo vo : list) {
			this.insert(vo);
		}
	} // insert
	
	
	public void deleteAttachfilesByBno(int bno) {
		attachfileMapper.deleteAttachfilesByBno(bno);
	}
	
	
	public void deleteAttachfileByUuid(String uuid) {
		attachfileMapper.deleteAttachfileByUuid(uuid);
	}
	
	
	public void deleteAttachfilesByUuids(List<String> uuids) {
		attachfileMapper.deleteAttachfilesByUuids(uuids);
	}
	
}
