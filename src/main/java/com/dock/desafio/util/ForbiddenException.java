package com.dock.desafio.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author luiz henrique
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ForbiddenException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ForbiddenException() {
        super();
    }
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
    public ForbiddenException(String message) {
        super(message);
    }
    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}