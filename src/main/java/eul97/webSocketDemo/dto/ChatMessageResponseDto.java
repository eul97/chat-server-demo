package eul97.webSocketDemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {

    private List<ChatMessageResponse> messageList = new ArrayList<>();

    public void addMessage(String writer, String message) {
        messageList.add(ChatMessageResponse.builder()
                .message(message)
                .writer(writer)
                .build());
    }

    @Getter
    @NoArgsConstructor
    private static class ChatMessageResponse {
        private String writer;
        private String message;

        @Builder
        public ChatMessageResponse(String writer, String message) {
            this.writer = writer;
            this.message = message;
        }
    }
}
