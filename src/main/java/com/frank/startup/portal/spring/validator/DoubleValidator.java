package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.frank.startup.portal.spring.constraint.DoubleConstraint;

/**
 * @ClassName:     PasswordValidator.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-10 下午3:37:04 
 */
public class DoubleValidator implements ConstraintValidator<DoubleConstraint, String>,
		BaseValidator {

	private DoubleConstraint doubleValue;
	@Override
	public void initialize(DoubleConstraint constraintAnnotation) {
		doubleValue = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!doubleValue.isMandatory()&&
				StringUtils.isBlank(value)){
			return true;
		}
		if (doubleValue.isMandatory()&&
				StringUtils.isBlank(value)) {
			return false;
		}
		
		String[] values = StringUtils.split(value, ".");
		
		if(values.length==2){
			try{
				Integer.parseInt(values[0]);
				Integer.parseInt(values[1]);
				if(values[0].length()>doubleValue.maxIntValue()||
						values[1].length()>doubleValue.scale()){
					throw new RuntimeException();
				}
				return true;
			}catch(Exception e){
				//ignore
			}
		}
		return false;
	}

}
