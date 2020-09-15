package com.example.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.java.Log;

//소켓 서버 역할 클래스
@Log
@Component
public class MyTextWebSocketHandler extends TextWebSocketHandler {
	
	private JSONParser jsonParser = new JSONParser();
	
	private Map<String, WebSocketSession> sessionMap = new HashMap<>();//웹소켓 세션을 담아둘 맵
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("웹소켓 클라이언트와 연결됨");
		
		//HttpSession에 있는 attributes값 가져오기
		Map<String, Object> attrsMap = session.getAttributes();
		String loginId = (String)attrsMap.get("id");
		log.info("loginId : " + loginId);
		
		//================================================================================
		
		//클라이언트 웹소켓 세션 관리를 위해 Map 컬렉션에 저장
		sessionMap.put(session.getId(), session);
		log.info(session.getId());
		
		// 세션아이디를 클라이언트로 보내기
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type",  "getId"); // 세션아이디를 데이터로 가진다는 표시
		jsonObject.put("sessionId",  session.getId());
		log.info("jsonObject : "  +  jsonObject.toJSONString());
		
		session.sendMessage(new TextMessage(jsonObject.toJSONString()));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		String jsonStr = message.getPayload();
		
		JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonStr);
		
		Set<String> keySet = sessionMap.keySet();
		
		//모든 채팅참여자에게 브로드캐스트 하기
		for(String key : keySet) {
			WebSocketSession wss = sessionMap.get(key);
			wss.sendMessage(new TextMessage(jsonObj.toJSONString()));
		}
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("웹소켓 클라이언트와 연결이 끊김");
		//웹소켓 세션이 종료되면 호출됨
		sessionMap.remove(session.getId());
	}
	

}
