package com.bilgeadam.NotesApp.controller;

import com.bilgeadam.NotesApp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "login")
public class LoginController {

    @GetMapping
    public ModelAndView login(){
        ModelAndView systemLoginView= new ModelAndView("login");
        systemLoginView.addObject("userinfo", new User());
        return systemLoginView;
    }
}
