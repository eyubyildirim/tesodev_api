package com.example.demo.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        log.info(ex.getMessage());
        final String bodyOfResponse = handle(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String handle(String message) {
        StringBuilder sb = new StringBuilder();
        if (message.contains("price")) {
            sb.append("price, ");
        }
        if (message.contains("quantity")) {
            sb.append("quantity, ");
        }
        if (message.contains("address.country")) {
            sb.append("address.country, ");
        }
        if (message.contains("address.city")) {
            sb.append("address.city, ");
        }
        if (message.contains("address.cityCode")) {
            sb.append("address.cityCode, ");
        }
        if (message.contains("customerId")) {
            sb.append("customerId, ");
        }
        sb.append("parameter(s) should be filled.");
        return sb.toString();
    }
}
