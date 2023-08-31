package com.trust.gestion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(String message) {
        super(message);
    }
    public OwnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public OwnerNotFoundException(Throwable cause) {
        super(cause);
    }
    public OwnerNotFoundException() {
        super();
    }
}
