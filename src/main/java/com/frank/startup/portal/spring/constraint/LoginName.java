package com.frank.startup.portal.spring.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.spring.validator.LoginNameValidator;

/**
 * @ClassName: LoginName.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-7-10 下午2:13:19
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginNameValidator.class)
public @interface LoginName {

	String message() default MessageConstant.ERROR_LOGIN_NAME_REQUIRED;

	int min();

	int max();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
