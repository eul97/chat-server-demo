package eul97.webSocketDemo.service;

import eul97.webSocketDemo.dto.ChatRoomResponseDto;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.repository.ChatMessageRepository;
import eul97.webSocketDemo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final Map<Long, List<WebSocketSession>> sessionTable = new HashMap<>();
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoomResponseDto createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);
        return ChatRoomResponseDto.create(chatRoom);
    }

    public List<ChatRoomResponseDto> getAllChatRoom() {
        List<ChatRoomResponseDto> responseDto = new ArrayList<>();
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        for (ChatRoom room : chatRooms) {
            responseDto.add(ChatRoomResponseDto.create(room));
        }

        return responseDto;
    }

    public ChatRoomResponseDto getChatRoom(Long id) throws Exception {
        return ChatRoomResponseDto.create(chatRoomRepository.findById(id).orElseThrow(Exception::new));
    }
}
