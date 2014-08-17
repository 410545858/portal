package com.frank.startup.portal.spring.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.frank.startup.portal.spring.constraint.Email;

/**
 * @ClassName: LoginNameValidator.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-7-108 下午2:34:05
 */
public class EmailValidator implements
		ConstraintValidator<Email, String>, BaseValidator {

	private String emailReg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	private Pattern emailPattern;
	private Email email;
	@Override
	public void initialize(Email constraintAnnotation) {
		email = constraintAnnotation;
		emailReg = emailReg.replace("min", String.valueOf(email.min())).replace("max", String.valueOf(email.max()));
		emailPattern = Pattern.compile(emailReg);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!email.isMandatory()&&
				StringUtils.isBlank(value)){
			return true;
		}
		
		if (email.isMandatory()&&
				StringUtils.isBlank(value)) {
			return false;
		}
		int length = value.length();
		if (length > email.max() || length < email.min()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_EMAIL_LENGTH).addConstraintViolation();
			return false;
		}
		if (!emailPattern.matcher(value).matches()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_EMAIL_PATTERN).addConstraintViolation();
			return false;
		}
		return true;
	}
}
