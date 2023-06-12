package kr.or.api.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import kr.or.api.service.IChatService;
import kr.or.api.vo.ChatContentVO;
import lombok.extern.slf4j.Slf4j;
/*
 * 절차 pom.xml, servlet-context.xml, TextWebSocketHandler(핸들러 작성)
 * 
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	@Inject
	IChatService chatService;
	
	// 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info(session.getId()+" 연결됨");
		sessions.add(session);
	}
	
	// 클라이언트와 연결이 종료되면 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("연결 종료");
		sessions.remove(session);
		
	}
	
	// 클라이언트로부터 메세지가 도착했을 시 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// TextMessage : 웹 소캣을 이용해 전달된 텍스트가 담겨있는 객체
		// payload : 전송되는 데이터(json 형태)
		log.info("메시지 전송 : " + message.getPayload());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ChatContentVO chatContent = objectMapper.readValue(message.getPayload(), ChatContentVO.class);
		chatContent.setChatDate(new Date(System.currentTimeMillis()));
		
		// 전달받은 데이터 db 삽입
		
		int result = chatService.insertMessage(chatContent);
		if(result > 0) {
			for(WebSocketSession s: sessions) {
				int chatRoomNo = (Integer)s.getAttributes().get("chatRNo");
				
				if(chatContent.getChatRNo() == chatRoomNo) {
					s.sendMessage(new TextMessage(new Gson().toJson(chatContent)));
				}
			}
		}
		
		super.handleTextMessage(session, message);
	}
	
	// 메세지 전송중, 에러가 발생하면 실행
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {	
		log.info("메시지 전송 에러");
	
	}
	
	
}
