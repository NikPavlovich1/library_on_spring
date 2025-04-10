package org.example.library_on_spring.dto;

import lombok.Value;
import org.example.library_on_spring.database.entity.Role;

@Value
public class AdminReadDto {

    private Long id;

    private String username;

    private Role role;
}
