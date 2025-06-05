package br.com.fiap.thermoscope.dtos.user.request;

import br.com.fiap.thermoscope.models.enums.UserRole;
import br.com.fiap.thermoscope.validations.enums.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PartialUpdateUserRequest {
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Email(message = "Email is not valid")
    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    private String email;

    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @ValidEnum(message = "Invalid role. Allowed values are FIREFIGHTER, ADMIN or COORDINATOR.", enumClass = UserRole.class)
    private String role;
}
