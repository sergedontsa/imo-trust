package com.trust.gestion.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ApiError(){
        timestamp = LocalDateTime.now();
    }
    ApiError(HttpStatus status){
        this();
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }
    ApiError(HttpStatus status, Throwable ex){
        this();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    ApiError(HttpStatus status, String message, Throwable ex){
        this();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
    ApiError(HttpStatus status, String message, List<String> errors){
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

}
