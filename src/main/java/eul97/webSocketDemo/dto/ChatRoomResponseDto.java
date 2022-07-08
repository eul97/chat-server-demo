package eul97.webSocketDemo.dto;

import eul97.webSocketDemo.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatRoomResponseDto {
    private String id;
    private String name;
    private Set<WebSocketSession> sessions;

    @Builder
    public ChatRoomResponseDto(String id, String name, Set<WebSocketSession> sessions) {
        this.id = id;
        this.name = name;
        this.sessions = sessions;
    }

    public static ChatRoomResponseDto convertChatRoomToDto(ChatRoom chatRoom) {

        return ChatRoomResponseDto.builder()
                .id(chatRoom.getRoomId().toString())
                .name(chatRoom.getName())
                .sessions(chatRoom.getSessions())
                .build();
    }
}
