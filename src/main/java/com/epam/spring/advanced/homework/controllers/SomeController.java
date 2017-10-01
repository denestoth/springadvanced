package com.epam.spring.advanced.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Denes Toth
 */
@Controller
public class SomeController {

    public String index(Model model) {
        model.addAttribute("someAttribute","someValue");
        return "index";
    }

}
