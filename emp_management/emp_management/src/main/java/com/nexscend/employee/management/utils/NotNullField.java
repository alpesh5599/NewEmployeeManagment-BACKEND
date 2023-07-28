package com.nexscend.employee.management.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNullField {
	
	String message() default "Field cannot be null or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
