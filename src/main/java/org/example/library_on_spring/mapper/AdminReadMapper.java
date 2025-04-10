package org.example.library_on_spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.Admin;
import org.example.library_on_spring.dto.AdminReadDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminReadMapper implements Mapper<Admin, AdminReadDto> {
    @Override
    public AdminReadDto map(Admin object) {
        return new AdminReadDto(
                object.getId(),
                object.getUsername(),
                object.getRole());
    }
}
