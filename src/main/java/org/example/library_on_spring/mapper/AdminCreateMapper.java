package org.example.library_on_spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.database.entity.Admin;
import org.example.library_on_spring.dto.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreateMapper implements Mapper<RegistrationRequest, Admin> {
    @Override
    public Admin map(RegistrationRequest object) {
        Admin admin = new Admin();
        copy(object, admin);
        return admin;
    }

    @Override
    public Admin map(RegistrationRequest fromObject, Admin toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(RegistrationRequest object, Admin admin) {
        admin.setUsername(object.getUsername());
        admin.setPassword(object.getPassword());
        admin.setRole(object.getRole());
    }
}
