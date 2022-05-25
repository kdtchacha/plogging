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
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class UserController {

    @Autowired 
    UserService userService;

    @GetMapping("/signin")
    public String signForm() {
        return "sign2";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        
    userService.signup(user);

        return "redirect:/index";
    }
    

    @PostMapping("/signin")
    public String signin(@ModelAttribute User user, HttpSession httpSession) {

        Optional<User> opt= userService.signin(user);

        httpSession.setAttribute("user", opt.get());

        return "indextemp";
    }

    @GetMapping("/signout")
    public String signout(HttpSession httpSession) {

        httpSession.invalidate();

        return "redirect:/index";
        
    }

    @GetMapping("/user/check")
    @ResponseBody
    public User userCheck(String email) {
        User user = userService.userCheck(email);
        return user;
        
    }

    
}

