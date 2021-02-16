package com.dock.desafio.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author luiz henrique
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RulesException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RulesException() {
        super();
    }
    public RulesException(String message, Throwable cause) {
        super(message, cause);
    }
    public RulesException(String message) {
        super(message);
    }
    public RulesException(Throwable cause) {
        super(cause);
    }
}