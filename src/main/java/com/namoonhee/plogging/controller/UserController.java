package com.namoonhee.plogging.controller;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    @Autowired 
    UserService userService;

    @GetMapping("/sign")
    public String signForm() {
        return "sign";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        
    userService.signup(user);

        return "redirect:/comeon";
    }
    

    @PostMapping("/signin")
    public String signin(@ModelAttribute User user, HttpSession httpSession) {

        Optional<User> opt= userService.signin(user);

        httpSession.setAttribute("user", opt.get());

        return "redirect:/comeon";
    }

    @GetMapping("/signout")
    public String signout(HttpSession httpSession) {

        httpSession.invalidate();

        return "signup";
        
    }
}

