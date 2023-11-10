package br.com.lbenaducci.inquisition.domain.exception;

public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}
