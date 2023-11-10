package br.com.lbenaducci.inquisition.domain.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
