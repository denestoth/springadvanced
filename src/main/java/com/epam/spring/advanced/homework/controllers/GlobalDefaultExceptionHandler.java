package com.epam.spring.advanced.homework.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Denes_Toth
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Model model, Exception e) throws Exception {
        return "error";
    }
}
