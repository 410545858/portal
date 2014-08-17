package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.frank.startup.portal.spring.constraint.ValidCode;

/**
 * @ClassName: ValidCodeValidator.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014年8月5日 下午4:24:56
 */
public class ValidCodeValidator implements
		ConstraintValidator<ValidCode, String>, BaseValidator {

	private ValidCode validCode;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(ValidCode constraintAnnotation) {
		validCode = constraintAnnotation;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String validCodeFiled, ConstraintValidatorContext arg1) {
		if(validCode.isMandatory()&&
				StringUtils.isBlank(validCodeFiled)){
			return false;
		}
		return true;
	}

}
