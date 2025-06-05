package br.com.fiap.thermoscope.controllers;

import br.com.fiap.thermoscope.dtos.auth.request.LoginRequest;
import br.com.fiap.thermoscope.dtos.auth.response.LoginResponse;
import br.com.fiap.thermoscope.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse login = authService.authenticateUser(loginRequest);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
