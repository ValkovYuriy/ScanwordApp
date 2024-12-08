package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.exception.WordAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
    public ModelAndView handleWordAlreadyExistsException(WordAlreadyExistsException e, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("error");
    }

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException e, Model model) {
//        log.error(e.getMessage(), e);
//        model.addAttribute("error", e.getMessage());
//        return ResponseEntity.ok(new ErrorResponse("ОК", e.getMessage()));
//    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ModelAndView modelAndView = new ModelAndView("work-with-dictionaries");
        modelAndView.addObject("errorMessage", "Размер файла превышает допустимый лимит!");
        return modelAndView;
    }

//    @ExceptionHandler
//    public String handleException(Throwable e, Model model) {
//        log.error(e.getMessage(), e);
//        model.addAttribute("errorMessage", e.getMessage());
//        return "work-with-dictionaries";
//    }


}
