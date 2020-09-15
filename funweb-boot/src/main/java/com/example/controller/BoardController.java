package com.example.controller;


import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AttachfileVo;
import com.example.domain.BoardVo;
import com.example.domain.PageDto;
import com.example.service.AttachfileService;
import com.example.service.BoardService;

import lombok.extern.java.Log;

@Log
@RequestMapping("/board/*")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachfileService attachService;
	
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1")int pageNum,
			@RequestParam(defaultValue = "")String category,
			@RequestParam(defaultValue = "")String search,
			Model model) {
		
		
		log.info("list() 호출됨");
		// 전체 글갯수
		int totalCount = boardService.getTotalCount(category, search);
		
		// 한 페이지에 15개씩 가져오기
		int pageSize = 15;
		// 시작행 인덱스번호 구하기(수식)
		int startRow = (pageNum-1) * pageSize;
			
		// 원하는 페이지의 글을 가져오는 메소드
		List<BoardVo> list = null;
		if (totalCount > 0) {
			list = boardService.getBoards(startRow, pageSize, category, search);
		}
		int pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount += 1;
		}
		// 화면에 보여줄 페이지번호의 갯수 설정
		int pageBlock = 10;
		
		// 페이지블록의 시작페이지 구하기!
		// 1~10   11~20   21~30
		
		//  1~10 ->  1
		// 11~20 -> 11
		// 21~30 -> 21
		// 31~40
		
		// 페이지 블록의 시작페이지
		int startPage = ((pageNum / pageBlock) - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
		// 페이지 블록의 끝페이지
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
	
		
		//페이지블록 관련 정보를 pageDto에 저장(map컬렉션 사용 가능)
//		Map<String, Object> pageMap = new HashMap<>();
//		pageMap.put("totalCount", totalCount);
//		pageMap.put("pageCount", pageCount);
//		pageMap.put("pageBlock", pageBlock);
//		pageMap.put("startPage", startPage);
//		pageMap.put("endPage", endPage);
//		pageMap.put("category", category);
//		pageMap.pur("search", search);
		
		
		PageDto pageDto = new PageDto();
		pageDto.setTotalCount(totalCount);
		pageDto.setPageCount(pageCount);
		pageDto.setPageBlock(pageBlock);
		pageDto.setStartPage(startPage);
		pageDto.setEndPage(endPage);
		pageDto.setCategory(category);
		pageDto.setSearch(search);
		
		
		//뷰(jsp)에서 사용할 데이터를 모델객체에 저장해놓으면
		//스프링 프론트컨트롤러가 request 영역객체로 옮겨줌
		model.addAttribute("boardList",  list);
		model.addAttribute("pageDto",  pageDto);
//		model.addAttribute("pageMap", pageMap);
		model.addAttribute("pageNum", pageNum);
		
		
		return "center/notice";
		
	}//list()
	
	@GetMapping("/write")
	public String write() {
		return "center/writeForm";
	}
	
	@PostMapping("/write")
	public String write(BoardVo boardVo, HttpServletRequest request) {
		//ip주소 작성일자 값 저장
		boardVo.setIp(request.getRemoteAddr());
		boardVo.setRegDate(LocalDateTime.now());
		
		// 주글 한개 등록
		boardService.insert(boardVo);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/content")
	public String content(int num, @ModelAttribute("pageNum") String pageNum, Model model) {
		//조회수 1증가
		boardService.updateReadcount(num);
		//글 한개 가져오기
		BoardVo vo = boardService.getBoardByNum(num);
		//내용에서 엔터키 줄바꿈 \r\n  ->  <br>  바꾸기
		String content ="";
		if(vo.getContent() != null){
			content = vo.getContent().replace("\r\n", "<br>");
			vo.setContent(content);
		}
		
		//jsp에서 사용할 데이터를 영역객체에 저장
		model.addAttribute("boardVo", vo);
//		model.addAttribute("pageNum",  pageNum); //@ModelAttribute로 대신함.
		//실행할 특정 jsp 경로정보 리턴
		return "center/content";
	}
	
	@GetMapping("/reply")
	public String reply(BoardVo boardVo, @ModelAttribute("pageNum")String pageNum) {
		//BoardVo에 reRef, reLev, reSeq 파라미터 설정됨
		//스프링 프론트컨트롤러는 우리메소드 파라미터에 VO형식 클래스가 선언되어 있으면
		//VO객체를 항상 model객체에 추가해주기때문에 뷰(JSP)에서 사용가능하다.
		//따라서 @ModelAttribute("boardVo")를 해줄 필요가 없다.
		
		
		return "center/replyWriteForm";
	}
	
	@PostMapping("/reply")
	public String reply(BoardVo boardVo, String pageNum, HttpServletRequest request, RedirectAttributes rttr) {
		//ip주소 작성일자 값 저장
		boardVo.setIp(request.getRemoteAddr());
		boardVo.setRegDate(LocalDateTime.now());
		
		//답글 insert하기
		boardService.replyInsert(boardVo);
		rttr.addAttribute("pageNum", pageNum);
//		return "redirect:/board/list?pagerNum="+pageNum;  //RedirectAttributes는 return " ?~~"에 들어갈 값 한번에 정리후 보내줌
		return "redirect:/board/list";
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String uuid) throws Exception{
		//uuid에 해당하는 레코드 한개 가져오기
		AttachfileVo attachVo = attachService.getAttachfileByUuid(uuid);
		//다운로드할 파일 객체 준비
		String filename = attachVo.getUuid() + "_" + attachVo.getFilename();
		File file = new File(attachVo.getUploadpath(), filename);
		
		Resource resource = new FileSystemResource(file);
		
		if(!resource.exists()) {
			System.out.println("다운로드할 파일이 존재하지 않습니다.");
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		String resFilename = resource.getFilename();
		
		// 다운로드할 파일이름에서 UUID 제거하기
		int beginIndex = resFilename.indexOf("_")+1;
		String originalFilename = filename.substring(beginIndex);
		
		// 다운로드 파일명의 문자셋을 utf-8에서 iso-8859-1로 변환
		String downloadFilename = new String(originalFilename.getBytes("utf-8"), "iso-8859-1");
		System.out.println("iso-8859-1 filename = " + downloadFilename);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + downloadFilename);
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
}
