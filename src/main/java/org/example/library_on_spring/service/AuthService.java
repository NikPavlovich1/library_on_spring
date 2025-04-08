package org.example.library_on_spring.service;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.Admin;
import org.example.library_on_spring.database.repository.AdminRepository;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.RegisterAdminRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerAdmin(RegisterAdminRequest request){
        if(adminRepository.existsByUsername(request.getUsername())){
            throw new IllegalStateException("Admin with username " + request.getUsername() + " already exists");
    }
        Admin admin = Admin.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .user(request.getUserId() != null ?
                        userRepository.findById(request.getUserId()).orElse(null) : null)
                .build();

        adminRepository.save(admin);
    }
}
