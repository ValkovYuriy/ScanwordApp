package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.exception.WordAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ModelAndView handleNotFoundException(NotFoundException e, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleWordAlreadyExistsException(WordAlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto<>(e.getMessage(),null));
    }


//    @ExceptionHandler
//    public ResponseEntity<ResponseDto<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        log.error(e.getMessage(), e);
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto<>("Ответ должен"))
//    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto<>("Ошибка на стороне сервера",null));
    }


}
