package eul97.webSocketDemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private Long id;
    private String name;

    @Builder
    public ChatRoomDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
