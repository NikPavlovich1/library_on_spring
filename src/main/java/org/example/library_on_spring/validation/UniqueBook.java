package org.example.library_on_spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.library_on_spring.validation.impl.UniqueBookValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueBookValidator.class)
public @interface UniqueBook {
    String message() default "Книга с такими данными уже существует";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
