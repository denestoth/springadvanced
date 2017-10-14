package com.epam.spring.advanced.homework.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

/**
 * @author Denes_Toth
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Model model, Exception e) throws Exception {
        StringBuilder sb = new StringBuilder(e.toString());
        sb.append("<br />");

        Arrays.stream(e.getStackTrace()).forEach(sse ->
        {
            sb.append(sse);
            sb.append("<br />");
        });

        model.addAttribute("errorMsg", sb.toString());
        return "error";
    }
}
