package com.frank.startup.portal.spring.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.StringValidator;

/**
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringValidator.class)
public @interface StringConstraint {

	String message() default MessageConstant.ERROR_REQUIRED;

	int min() default 1;

	int max();

	boolean isMandatory() default true;
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
