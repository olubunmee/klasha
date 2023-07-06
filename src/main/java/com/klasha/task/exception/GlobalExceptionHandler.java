package com.klasha.task.exception;

import com.klasha.task.dto.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorController {

    private final MessageSource messageSource;

    @ExceptionHandler({KlashaException.class})
    public ResponseEntity<ApiResponse<String>> handleKlashaException(KlashaException e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("An expected exception was thrown :: ", ex);
        BindingResult result = ex.getBindingResult();
        Locale locale = new Locale("en");
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(err -> messageSource.getMessage(err, locale))
                .toList();
        return new ResponseEntity<>(new ErrorMessage(errorMessages.toString()), HttpStatus.BAD_REQUEST);
    }
}