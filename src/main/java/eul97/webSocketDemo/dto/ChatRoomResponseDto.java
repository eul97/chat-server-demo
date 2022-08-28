package eul97.webSocketDemo.dto;

import eul97.webSocketDemo.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomResponseDto {
    private Long id;
    private String name;

    @Builder
    public ChatRoomResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ChatRoomResponseDto convert(ChatRoom chatRoom) {

        return ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .name(chatRoom.getName())
                .build();
    }
}
