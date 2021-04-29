package com.luana.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErr extends StandartError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
			
	public ValidationErr(Integer status, String msg, Long time) {
		super(status, msg, time);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	
	}


}
