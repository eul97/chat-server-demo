package eul97.webSocketDemo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatmessage_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private String message;

    private String writer;

    @Builder
    public ChatMessage(ChatRoom chatRoom, String message, String writer) {
        this.chatRoom = chatRoom;
        this.message = message;
        this.writer = writer;
    }
}
