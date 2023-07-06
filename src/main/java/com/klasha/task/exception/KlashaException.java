package com.klasha.task.exception;

import lombok.Getter;

@Getter
public class KlashaException extends Exception {

    public KlashaException(String message) {
        super(message);
    }

}