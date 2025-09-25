package com.crypto.alerts.user_service.controller;

import com.crypto.alerts.user_service.dto.UserRequestDTO;
import com.crypto.alerts.user_service.dto.UserResponseDTO;
import com.crypto.alerts.user_service.security.JwtUtil;
import com.crypto.alerts.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final JwtUtil jwtUtil;

    public UserController(UserService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO dto) {
        return service.createUser(dto);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        String token = service.login(email, password);
        return Map.of("token", token);
    }

    @GetMapping
    public List<UserResponseDTO> all() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO one(@PathVariable UUID id) {
        return service.getUser(id);
    }
}
