package com.osama.product_service.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalValidatorFactoryBean.class)
public @interface ValidLocalizedEntries {
    String message() default "Invalid localized entries";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
