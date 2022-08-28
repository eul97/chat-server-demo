package eul97.webSocketDemo.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String name;

    @NotNull
    private String password;

    @Builder
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
