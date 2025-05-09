package com.laboratorio03.campuscash.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping("/home")
    public ModelAndView helloWorld() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("message", "Hello World!");
        mv.addObject("page", "home");
        return mv;
    }
}
