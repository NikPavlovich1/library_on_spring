package org.example.library_on_spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.validation.UniqueUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUserValidator implements ConstraintValidator<UniqueUserName, UserCreateEditDto> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(UserCreateEditDto userDto, ConstraintValidatorContext context) {
        if (userDto == null) {
            return true;
        }
        boolean exists = userRepository.existsByFirstnameAndLastname(
                userDto.getFirstname(),
                userDto.getLastname()
        );
        return !exists;
    }
}