package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.exception.SizeLimitException;
import com.quad.ScanwordApp.exception.WordAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(e.getMessage(),null));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleWordAlreadyExistsException(WordAlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto<>(e.getMessage(),null));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleSizeLimitException(SizeLimitException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>(e.getMessage(),null));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return "access-denied";
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto<>("Ошибка на стороне сервера",null));
    }


}
