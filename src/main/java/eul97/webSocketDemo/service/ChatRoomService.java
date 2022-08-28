package eul97.webSocketDemo.service;

import eul97.webSocketDemo.dto.ChatMessageResponseDto;
import eul97.webSocketDemo.dto.ChatRoomDto;
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

    public void createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);
    }

    public ChatRoomResponseDto getAllChatRoom() {
        ChatRoomResponseDto responseDto = new ChatRoomResponseDto();
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        chatRooms.forEach(responseDto::convert);

        return responseDto;
    }

    public ChatRoomDto getChatRoom(Long id) throws Exception {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(Exception::new);
        return ChatRoomDto.builder()
                .name(chatRoom.getName())
                .id(chatRoom.getId())
                .build();
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
