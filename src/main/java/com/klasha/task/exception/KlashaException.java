package com.klasha.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KlashaException extends Exception {

    public KlashaException(String message) {
        super(message);
    }

}