package br.com.microservice.exception;


@SuppressWarnings("serial")
public class FieldException extends Exception {

	public FieldException(String errorMessage) {
		super(errorMessage);
	}
}
