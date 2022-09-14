package me.project.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArgumentNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ArgumentNotValidException(String message) {
        super(message);
    }
}
