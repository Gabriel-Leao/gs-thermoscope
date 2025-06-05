package br.com.fiap.thermoscope.dtos.user.response;

import br.com.fiap.thermoscope.entities.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserResponse {
    private final UUID id;
    private final String name;
    private final String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
