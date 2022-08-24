package eul97.webSocketDemo.controller;

import eul97.webSocketDemo.dto.ChatRoomRequestDto;
import eul97.webSocketDemo.dto.ChatRoomResponseDto;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate template;

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList() {
        return ResponseEntity.ok(chatRoomService.getAllChatRoom());
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<ChatRoomResponseDto> room(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(chatRoomService.getChatRoom(id));
    }

    @PostMapping("/new")
    public ResponseEntity<ChatRoomResponseDto> makeRoom(ChatRoomRequestDto requestDto) {
        return ResponseEntity.ok(chatRoomService.createChatRoom(requestDto.getName()));
    }


}
