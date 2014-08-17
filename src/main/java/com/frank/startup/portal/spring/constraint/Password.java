package com.frank.startup.portal.spring.constraint;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.PasswordValidator;

/**
 * @ClassName:     Password.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-10 下午3:37:32 
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

	String message() default MessageConstant.ERROR_LOGIN_PASSWORD_REQUIRED;

	int min();

	int max();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
