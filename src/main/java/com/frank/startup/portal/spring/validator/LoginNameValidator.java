package com.frank.startup.portal.spring.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.frank.startup.portal.spring.constraint.LoginName;

/**
 * @ClassName: LoginNameValidator.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-7-10 下午2:34:05
 */
public class LoginNameValidator implements
		ConstraintValidator<LoginName, String>, BaseValidator {

	private String loginNameReg = "^[a-zA-Z_]\\w{min,max}$";
	private Pattern loginNamePattern;
	private LoginName loginName;
	@Override
	public void initialize(LoginName constraintAnnotation) {
		loginName = constraintAnnotation;
		loginNameReg = loginNameReg.replace("min", String.valueOf(loginName.min())).replace("max", String.valueOf(loginName.max()));
		loginNamePattern = Pattern.compile(loginNameReg);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.equals("")) {
			return false;
		}
		int length = value.length();
		if (length > loginName.max() || length < loginName.min()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_LOGIN_NAME_LENGTH).addConstraintViolation();
			return false;
		}
		if (!loginNamePattern.matcher(value).matches()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_LENGTH).addConstraintViolation();
		}
		return true;
	}
}
