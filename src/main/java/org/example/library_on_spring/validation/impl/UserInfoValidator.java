package org.example.library_on_spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.validation.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) && StringUtils.hasText(value.getLastname());
    }
}
