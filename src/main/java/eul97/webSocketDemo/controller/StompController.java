package eul97.webSocketDemo.controller;

import eul97.webSocketDemo.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompController {

    //private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/message")
    public void message(ChatMessageDto dto) {
        log.info("message : " + dto.getMessage());
        log.info("writer : " + dto.getWriter());
        log.info("roomId : " + dto.getRoomId());
        //simpMessageSendingOperations.convertAndSend("sub/chat/room/" + dto.getRoomId(), dto);
        simpMessagingTemplate.convertAndSend("sub/room/" + dto.getRoomId(), dto);
    }
}
