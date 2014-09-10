/**
 * 
 */
package com.frank.startup.portal.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.frank.startup.portal.spring.constraint.DoubleRangeConstraint;

/**
 * @author frankwong
 *
 */
public class DoubleRangeValidator implements ConstraintValidator<DoubleRangeConstraint, Double>, BaseValidator {

	private DoubleRangeConstraint doubleValue;

	@Override
	public void initialize(DoubleRangeConstraint constraintAnnotation) {
		doubleValue = constraintAnnotation;

	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value.compareTo(doubleValue.max()) > 0 || value.compareTo(doubleValue.min()) < 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ERROR_RANGE_DOUBLE).addConstraintViolation();
			return false;
		}
		return true;
	}

}
