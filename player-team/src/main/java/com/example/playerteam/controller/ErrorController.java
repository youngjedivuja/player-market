package com.example.playerteam.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestController
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler({
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class
    })
    protected ResponseEntity<ResponseError> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getResponseError(ex.getMessage(), request, status);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class
    })
    protected ResponseEntity<ResponseError> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getResponseError(ex.getMessage(), request, status);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            Exception.class
    })
    protected ResponseEntity<ResponseError> handleInternalServerError(RuntimeException ex, HttpServletRequest request) {
        ex.printStackTrace();

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            String message = responseStatus.reason();
            if (ex.getMessage() != null)
                message = String.format("%s: %s", responseStatus.reason(), ex.getMessage());

            return getResponseError(message, request, responseStatus.code());
        }
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getResponseError(ex.getMessage(), request, status);
    }

    private ResponseEntity<ResponseError> getResponseError(String message, HttpServletRequest request, HttpStatus status) {
        ResponseError errorInfo = new ResponseError(status, request, message);
        return ResponseEntity.status(errorInfo.getCode()).body(errorInfo);
    }

    @Data
    @AllArgsConstructor
    public static final class ResponseError {
        private String message;
        private Integer code;
        private String url;
        private String exception;

        public ResponseError(HttpStatus status, HttpServletRequest request, String exception) {
            this.message = status.getReasonPhrase();
            this.code = status.value();
            this.url = request.getRequestURI();
            this.exception = exception;
        }
    }
}
