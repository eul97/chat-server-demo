package eul97.webSocketDemo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    // DB테이블에는 존재 x, 엔티티 클래스에는 포함
    @Transient
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void deleteSession(WebSocketSession session) {
        sessions.remove(session);
    }
}
