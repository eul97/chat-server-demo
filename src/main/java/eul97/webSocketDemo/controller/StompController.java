package eul97.webSocketDemo.controller;

import eul97.webSocketDemo.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompController {

    private final SimpMessagingTemplate template;


    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto dto) {
        template.convertAndSend("sub/chat/room/" + dto.getRoomId(), dto.getWriter() + " : " + dto.getMessage());
    }
}
