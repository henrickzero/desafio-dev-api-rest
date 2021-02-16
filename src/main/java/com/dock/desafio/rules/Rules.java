package com.dock.desafio.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author luiz henrique
 * Classe base de regras de negocio
 *
 */
public class Rules {

	public List<String>  valid(Object obj, String ... ignore) {
		
		List<String> list = ignore!=null?Arrays.asList(ignore):new ArrayList<String>();
		List<String> erros = new ArrayList<String>();
		Set<ConstraintViolation<Object>> violations = validator(obj);
		for (ConstraintViolation<Object> violation : violations) {
			if(!list.contains(violation.getPropertyPath().toString())) {
				 erros.add(violation.getPropertyPath()+": "+violation.getMessage());
			}
		}
		return erros;
	}
	private <K> Set<ConstraintViolation<K>> validator (K obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		 return validator.validate(obj);
	}
}
