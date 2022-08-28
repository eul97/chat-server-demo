package eul97.webSocketDemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String password;

    @Builder
    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
