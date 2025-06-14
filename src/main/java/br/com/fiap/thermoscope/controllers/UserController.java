package br.com.fiap.thermoscope.controllers;

import br.com.fiap.thermoscope.dtos.user.request.DeleteUserRequest;
import br.com.fiap.thermoscope.dtos.user.request.PartialUpdateUserRequest;
import br.com.fiap.thermoscope.dtos.user.request.UpsertUserRequest;
import br.com.fiap.thermoscope.dtos.user.response.CreateUserResponse;
import br.com.fiap.thermoscope.dtos.user.response.UserResponse;
import br.com.fiap.thermoscope.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse user = new UserResponse(userService.getUserById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody UpsertUserRequest user) {
        CreateUserResponse createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UpsertUserRequest user) {
        UserResponse updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partialUpdateUser(@PathVariable UUID id, @Valid @RequestBody PartialUpdateUserRequest userRequest) {
        UserResponse updatedUser = userService.partialUpdateUser(id, userRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUSer(@PathVariable UUID id, @Valid @RequestBody DeleteUserRequest user) {
        userService.deleteUser(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
