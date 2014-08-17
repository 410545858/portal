package com.frank.startup.portal.spring.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.PhoneConstraintValidator;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

	
	String message() default MessageConstant.ERROR_PHONE;
	
	boolean isMandatory() default true;
	
	boolean onlyMobile() default true;
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	 
}