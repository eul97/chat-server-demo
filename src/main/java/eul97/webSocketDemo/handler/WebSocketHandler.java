package eul97.webSocketDemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eul97.webSocketDemo.entity.ChatMessage;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.repository.ChatRoomRepository;
import eul97.webSocketDemo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatRoomService chatRoomService;
    private final ObjectMapper objectMapper;

//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // 연결되었을 때 실행
//        sessions.add(session);
//        log.info("접속 : {}", session);
//    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 메시지 전송했을 때
        log.info("메시지 전송 = {} : {}", session, message.getPayload());
//        // 접속한 세션들 모두에게 메시지를 뿌려준다
//        for (WebSocketSession wss : sessions) {
//            TextMessage msg = new TextMessage((CharSequence) message.getPayload());
//            wss.sendMessage(msg);
//        }

        String msg = (String) message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(msg, ChatMessage.class);
        chatRoomService.handleMessage(session, chatMessage, objectMapper);

    }

//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        // 연결이 끊어졌을 때때
//        sessions.remove(session);
//        log.info("퇴장 : {}", session);
//    }
}
