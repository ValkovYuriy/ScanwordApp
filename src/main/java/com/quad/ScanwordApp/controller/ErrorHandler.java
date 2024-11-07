package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ModelAndView handleNotFoundException(NotFoundException e, Model model) {
        log.error(e.getMessage(),e);
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("error");
    }

//    @ExceptionHandler
//    public ModelAndView handleException(Throwable e, Model model) {
//        log.error(e.getMessage(),e);
//        model.addAttribute("error", e.getMessage());
//        return new ModelAndView("error");
//    }

}
