package com.frank.startup.portal.spring.constraint;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.DoubleValidator;

/**
 * @ClassName:     Password.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-10 下午3:37:32 
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DoubleValidator.class)
public @interface DoubleConstraint {

	String message() default MessageConstant.ERROR_DOUBLE;
	
	int scale() default 2;
	int maxIntValue();
	boolean isMandatory () default true;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
