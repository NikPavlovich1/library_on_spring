package org.example.library_on_spring.service;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.Admin;
import org.example.library_on_spring.database.repository.AdminRepository;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.AdminReadDto;
import org.example.library_on_spring.dto.RegistrationRequest;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.dto.UserReadDto;
import org.example.library_on_spring.mapper.AdminCreateMapper;
import org.example.library_on_spring.mapper.AdminReadMapper;
import org.example.library_on_spring.mapper.UserReadMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminReadMapper adminReadMapper;
    private final AdminCreateMapper adminCreateMapper;

    public boolean existsByUsername(String username) {
        return adminRepository.findByUsername(username).isPresent();
    }

    public List<AdminReadDto> findAll() {
        return adminRepository.findAll().stream()
                .map(adminReadMapper::map)
                .toList();
    }
    @Transactional
    public AdminReadDto create(RegistrationRequest request) {
        return Optional.of(request)
                .map(adminCreateMapper::map)
                .map(adminRepository::save)
                .map(adminReadMapper::map)
                .orElseThrow();
    }

    public void registerAdmin(RegistrationRequest request){
        if(adminRepository.existsByUsername(request.getUsername())){
            throw new IllegalStateException("Admin with username " + request.getUsername() + " already exists");
    }
        Admin admin = Admin.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        adminRepository.save(admin);
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}
