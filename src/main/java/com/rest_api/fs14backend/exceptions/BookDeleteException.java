package com.rest_api.fs14backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookDeleteException extends RuntimeException{
    public BookDeleteException(String message) {
        super(message);
    }
}
