package com.crypto.alerts.user_service.service;

import com.crypto.alerts.user_service.dto.UserRequestDTO;
import com.crypto.alerts.user_service.dto.UserResponseDTO;
import com.crypto.alerts.user_service.entity.User;
import com.crypto.alerts.user_service.repository.UserRepository;
import com.crypto.alerts.user_service.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), encodedPassword);
        User saved = repo.save(user);
        return new UserResponseDTO(saved.getId().toString(), saved.getName(), saved.getEmail());
    }

    public List<UserResponseDTO> getAllUsers() {
        return repo.findAll().stream()
                .map(u -> new UserResponseDTO(u.getId().toString(), u.getName(), u.getEmail()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUser(UUID id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId().toString(), user.getName(), user.getEmail());
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String login(String email, String password) {
        User user = findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return JwtUtil.generateToken(user);
    }
}
