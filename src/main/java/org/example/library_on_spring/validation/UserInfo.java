package org.example.library_on_spring.validation;

import jakarta.validation.Constraint;
import org.example.library_on_spring.validation.impl.UserInfoValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserInfoValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface UserInfo {

    String message() default "Имя и фамилия должны быть заполнены";

    Class<?>[] groups() default {};

    Class<? extends Constraint>[] payload() default {};

}
