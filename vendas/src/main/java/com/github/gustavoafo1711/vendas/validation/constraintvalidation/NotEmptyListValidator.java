package com.github.gustavoafo1711.vendas.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.gustavoafo1711.vendas.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
		return list != null && !list.isEmpty();
	}

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
	}
	

}
