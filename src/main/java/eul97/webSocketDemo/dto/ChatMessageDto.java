package eul97.webSocketDemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto {

    private String roomId;
    private String writer;
    private String message;

    @Builder
    public ChatMessageDto(String roomId, String writer, String message) {
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
    }
}
