package com.klasha.task.exception;

import com.klasha.task.dto.responses.ApiResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorController {


    @ExceptionHandler({KlashaException.class})
    public ResponseEntity<ApiResponse<String>> handleKlashaException(KlashaException e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException ex) {
        Matcher matcher = Pattern.compile("\"msg\":\"(.*?)\"").matcher(ex.getMessage());
        String res = null;
        if (matcher.find()) {
            res = matcher.group(1);
        }
        return ResponseEntity.badRequest().body(ApiResponse.error(res));
    }


}