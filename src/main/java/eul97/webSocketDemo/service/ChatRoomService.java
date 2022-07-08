package eul97.webSocketDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eul97.webSocketDemo.entity.ChatMessage;
import eul97.webSocketDemo.entity.ChatRoom;
import eul97.webSocketDemo.entity.MessageType;
import eul97.webSocketDemo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
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

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public List<ChatRoom> getAllChatRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoom(Long id) throws Exception {
        return chatRoomRepository.findById(id).orElseThrow(Exception::new);
    }

    public void handleMessage(WebSocketSession session, ChatMessage message, ObjectMapper objectMapper) throws Exception {
        ChatRoom chatRoom = chatRoomRepository.findById(message.getChatRoomId()).orElseThrow(Exception::new);
        switch (message.getType()) {
            case ENTER:
                message.setMessage(message.getWriter() + "님이 입장하셨습니다");
                if (!sessionTable.containsKey(chatRoom.getRoomId()))
                    sessionTable.put(chatRoom.getRoomId(), new ArrayList<>());
                sessionTable.get(chatRoom.getRoomId()).add(session);
                break;
            case LEAVE:
                message.setMessage(message.getWriter() + "님이 퇴장하셨습니다");
                sessionTable.get(chatRoom.getRoomId()).remove(session);
                break;
            case CHAT:
                message.setMessage(message.getWriter() + ": " + message.getMessage());
        }
        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message.getMessage()));


        for (WebSocketSession chatRoomSession : sessionTable.get(chatRoom.getRoomId())) {
            chatRoomSession.sendMessage(textMessage);
        }
    }
}
