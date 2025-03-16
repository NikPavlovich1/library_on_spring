package org.example.library_on_spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.library_on_spring.validation.impl.UniqueUserValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUserName {
    String message() default "Пользователь с такими данными уже существует";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}