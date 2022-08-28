package eul97.webSocketDemo.controller;

import eul97.webSocketDemo.dto.ChatMessageResponseDto;
import eul97.webSocketDemo.dto.ChatRoomRequestDto;
import eul97.webSocketDemo.dto.ChatRoomResponseDto;
import eul97.webSocketDemo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList() {
        return ResponseEntity.ok(chatRoomService.getAllChatRoom());
    }

    // 특정 채팅방 정보 조회
    @GetMapping("/rooms/{id}")
    public ResponseEntity<ChatRoomResponseDto> room(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(chatRoomService.getChatRoom(id));
    }

    // 채팅방의 메시지 조회
    @GetMapping("/rooms/messages/{id}")
    public ResponseEntity<ChatMessageResponseDto> roomMessage(@PathVariable Long id) {
        return ResponseEntity.ok(chatRoomService.getMessages(id));
    }

    // 채팅방 신규 생성
    @GetMapping("/rooms/new")
    public void makeRoom(@RequestParam ChatRoomRequestDto requestDto) {
        chatRoomService.createChatRoom(requestDto.getName());
    }
}
