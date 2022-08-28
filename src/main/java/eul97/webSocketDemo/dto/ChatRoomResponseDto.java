package eul97.webSocketDemo.dto;

import eul97.webSocketDemo.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private List<ChatRoomDto> chatRooms = new ArrayList<>();

    public void convert(ChatRoom chatRoom) {
        chatRooms.add(ChatRoomDto.builder()
                .name(chatRoom.getName())
                .id(chatRoom.getId())
                .build());
    }
}

