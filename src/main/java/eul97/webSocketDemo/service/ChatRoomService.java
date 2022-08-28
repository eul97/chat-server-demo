package eul97.webSocketDemo.service;

import eul97.webSocketDemo.dto.ChatMessageResponseDto;
import eul97.webSocketDemo.dto.ChatRoomResponseDto;
import eul97.webSocketDemo.entity.ChatMessage;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.repository.ChatMessageRepository;
import eul97.webSocketDemo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

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
        return ChatRoomResponseDto.convert(chatRoom);
    }

    public List<ChatRoomResponseDto> getAllChatRoom() {
        List<ChatRoomResponseDto> responseDto = new ArrayList<>();
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        for (ChatRoom room : chatRooms) {
            responseDto.add(ChatRoomResponseDto.convert(room));
        }

        return responseDto;
    }

    public ChatRoomResponseDto getChatRoom(Long id) throws Exception {
        return ChatRoomResponseDto.convert(chatRoomRepository.findById(id).orElseThrow(Exception::new));
    }


    public ChatMessageResponseDto getMessages(Long chatRoomId) {
        Optional<ChatRoom> room = chatRoomRepository.findById(chatRoomId);
        if (room.isEmpty()) throw new IllegalArgumentException();

        List<ChatMessage> messages = room.get().getChatMessageList();
        ChatMessageResponseDto responseDto = new ChatMessageResponseDto();
        for (ChatMessage message : messages) {
            responseDto.addMessage(message.getWriter(), message.getMessage());
        }

        return responseDto;
    }
}
