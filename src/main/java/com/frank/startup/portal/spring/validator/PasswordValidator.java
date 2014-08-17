package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.frank.startup.portal.spring.constraint.Password;

/**
 * @ClassName:     PasswordValidator.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-10 下午3:37:04 
 */
public class PasswordValidator implements ConstraintValidator<Password, String>,
		BaseValidator {

	private Password password;
	@Override
	public void initialize(Password constraintAnnotation) {
		password = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.equals("")) {
			return false;
		}
		int length = value.length();
		if (length > password.max() || length < password.min()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_LOGIN_PASSWORD_LENGTH).addConstraintViolation();
			return false;
		}
		return true;
	}

}
