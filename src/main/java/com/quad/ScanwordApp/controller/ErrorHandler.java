package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(),e);
        return new ErrorResponse("ОК",e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse handleException(Throwable e) {
        log.error(e.getMessage(),e);
        return new ErrorResponse("ОК",e.getMessage());
    }

}
