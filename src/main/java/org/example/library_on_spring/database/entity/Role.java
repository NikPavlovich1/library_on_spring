package org.example.library_on_spring.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPERUSER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
