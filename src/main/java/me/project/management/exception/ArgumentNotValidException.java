package me.project.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArgumentNotValidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ArgumentNotValidException(String message) {
		super(message);
	}
}
