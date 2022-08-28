package eul97.webSocketDemo.service;

import eul97.webSocketDemo.dto.ChatMessageDto;
import eul97.webSocketDemo.entity.ChatMessage;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.repository.ChatMessageRepository;
import eul97.webSocketDemo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void saveMessage(ChatMessageDto messageDto) {
        Optional<ChatRoom> room = chatRoomRepository.findById(Long.valueOf(messageDto.getRoomId()));
        room.ifPresent(chatRoom -> chatMessageRepository.save(ChatMessage.builder()
                .message(messageDto.getMessage())
                .chatRoom(chatRoom)
                .writer(messageDto.getWriter())
                .build()
        ));
    }

}
