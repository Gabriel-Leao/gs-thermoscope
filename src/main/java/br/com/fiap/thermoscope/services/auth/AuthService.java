package br.com.fiap.thermoscope.services.auth;

import br.com.fiap.thermoscope.dtos.auth.request.LoginRequest;
import br.com.fiap.thermoscope.dtos.auth.response.LoginResponse;
import br.com.fiap.thermoscope.exceptions.InvalidCredentialsException;
import br.com.fiap.thermoscope.models.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticateUser(LoginRequest loginRequestDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new LoginResponse(jwtService.generateToken(userDetails));
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new InvalidCredentialsException();
        }
    }
}
