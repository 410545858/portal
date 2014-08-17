package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.frank.startup.portal.spring.constraint.StringConstraint;

/**
 * @ClassName:     StringValidator.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */

public class StringValidator implements
		ConstraintValidator<StringConstraint, String>, BaseValidator {

	private StringConstraint stringValue;
	@Override
	public void initialize(StringConstraint constraintAnnotation) {
		stringValue = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!stringValue.isMandatory()&&
				StringUtils.isBlank(value)){
			return true;
		}
		
		if (stringValue.isMandatory()&&
				StringUtils.isBlank(value)) {
			return false;
		}
		int length = value.length();
		if (length > stringValue.max() || length < stringValue.min()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					ERROR_LENGTH).addConstraintViolation();
			return false;
		}
		return true;
	}
}
