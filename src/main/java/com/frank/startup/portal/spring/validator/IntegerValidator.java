package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.frank.startup.portal.spring.constraint.IntegerConstraint;

/**
 * @ClassName:     PasswordValidator.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-10 下午3:37:04 
 */
public class IntegerValidator implements ConstraintValidator<IntegerConstraint, String>,
		BaseValidator {

	private IntegerConstraint integerValue;
	@Override
	public void initialize(IntegerConstraint constraintAnnotation) {
		integerValue = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!integerValue.isMandatory()&&
				StringUtils.isBlank(value)){
			return true;
		}
		
		if (integerValue.isMandatory()&&
				StringUtils.isBlank(value)) {
			return false;
		}
		try{
			Integer intValue = Integer.parseInt(value);
			if(intValue>=integerValue.min()&&intValue<=integerValue.max()){
				return true;
			}
		}catch(Exception e){
			//ignore
		}
		return false;
	}

}
