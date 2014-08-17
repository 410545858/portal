package com.frank.startup.portal.spring.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.ValidCodeValidator;

/**
 * @ClassName:     ValidCodeConstraint.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014年8月5日 下午4:25:27 
 */

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCodeValidator.class)
public @interface ValidCode {

	String message() default MessageConstant.ERROR_CAPTCHA_REQUIRED;
	
	boolean isMandatory() default true;
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
