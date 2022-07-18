package com.example.homework210.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadNamesException extends RuntimeException {
    public BadNamesException(){};
}
