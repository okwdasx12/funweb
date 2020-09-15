package com.example.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.MemberVo;
import com.example.service.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService MemberService; 
	
	//@Controller 애노테이션이 있어야 GetMapping이 연동이 됨
	//@RequestMapping(value="/join",method=RequestMethod.GET) 			예전방식
	@GetMapping("/join")//												요즘방식
	public String join() {
		return"member/join";
		//리턴타입이 void면 메소드 이름을 jsp경로로 사용하여 실행
	}
	
	@PostMapping("/join")
	public String join(MemberVo memberVo) {//mvc타입 1의 jsp:useBean 효과
		//return "member/join"; //메소드 리턴타입이 String일때
		
		//regDate 가입날짜 생성해서 넣기
		memberVo.setRegDate(LocalDateTime.now());
		//회원가입 처리
		MemberService.insert(memberVo);
		
		log.info(memberVo.toString());
		
		return "redirect:/member/login";
	}
	
	@RequestMapping("/joinIdDupCheck")
	public String joinIdDupCheck(String id, Model model) {//값을 찾아서 매개변수에 전달해줌
		log.info("id : " + id);

		//아이디 중복여부 값 구하기
		boolean isIdDup = MemberService.isIdDuplicated(id);
		
		//model 타입 객체에 뷰(JSP)에서 사용할 데이터를 저장하기(싣기)
		model.addAttribute("isIdDup", isIdDup); //frontcontroller가 setAttribute해줌
		model.addAttribute("id", id);
		
		return "member/join_IDCheck";
	}
	
	@GetMapping("/ajaxJoinIdDupCheck")
	//@ResponseBody 애노테이션을 통해서 리턴값을 JSON형식으로 응답해준다.
	public @ResponseBody Map<String, Object> ajaxJoinIdDupCheck(String id) {
		log.info("id : " + id);
		
		if(id == null || id.length()==0) {
			return null;
		}
		//아이디 중복여부 값 구하기
		boolean isIdDup = MemberService.isIdDuplicated(id);
		
		Map<String, Object> map = new HashMap<>();
			map.put("isIdDup", isIdDup);
			map.put("name", "홍길동");
			
		return map;
	}
	
	
	@GetMapping("/login")
	public void login() {
		log.info("login()");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(String id, String passwd, @RequestParam(defaultValue = "false")boolean keepLogin,
			HttpSession session, HttpServletResponse response) {
		int check = MemberService.userCheck(id, passwd);
		if(check != 1) {
			String message="";
			if(check==0) {
				message="비밀번호 틀림";
			} else if( check ==-1) {
				message="아이디 없음";
			} 
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("<script>");
			
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		//로그인 성공시
		//세션에 아이디 저장(로그인 인증)
		session.setAttribute("id",  id);
		log.info("id : " + id);
		//로그인 상태유지 원하면 쿠키 생성 후 응답주기
		if (keepLogin) { // keepLogin == true
			Cookie idCookie = new Cookie("id", id);
			idCookie.setMaxAge(60*10); // (초단위 설정) 10분
			idCookie.setPath("/"); //쿠키경로 설정
			response.addCookie(idCookie); //응답객체에 추가
		}
		//return "redirect:/";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/"); //redirect 경로 위치 지정
		//리다이렉트일 경우 HttpStatus.FOUND 지정해야함 
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}//login
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		//세션값 초기화
		session.invalidate();
		
		//로그인 상태유지용 쿠키가 존재하면 삭제
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("id")){
					cookie.setMaxAge(0); //유효기간 0 설정
					cookie.setPath("/");//경로설정
					response.addCookie(cookie);
				}
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('로그아웃됨');");
		sb.append("location.href= '/';");
		sb.append("</script>");
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		
	}//logout
}
