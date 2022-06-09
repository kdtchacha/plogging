package com.namoonhee.plogging.controller;

import java.util.List;

import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    


    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActAnswerRepository actAnswerRepository;


    @GetMapping("/adminmain")
    public String adminMain() {    
        
        return "adminmain";
    }

    @ResponseBody
    @GetMapping("/adminUser")
    public List<User> adminUser(){

       List<User> users = userRepository.findAll();

        return users;
    }





}