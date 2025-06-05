package br.com.fiap.thermoscope.dtos.user.response;

import br.com.fiap.thermoscope.entities.User;
import lombok.Getter;

@Getter
public class CreateUserResponse extends UserResponse {
    private final String token;

    public CreateUserResponse(User user, String token) {
        super(user);
        this.token = token;
    }
}
