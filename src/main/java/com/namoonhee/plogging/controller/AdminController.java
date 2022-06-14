package com.namoonhee.plogging.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.ActAnswer;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.repository.UserRepository;
import com.namoonhee.plogging.service.ActivityService;

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

    @Autowired
    ActivityService activityService;

    @GetMapping("/adminmain")
    public String adminMain() {

        return "adminmain";
    }

    @ResponseBody
    @GetMapping("/adminUser")
    public List<User> adminUser() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @ResponseBody
    @GetMapping("/adminActivity")
    public List<Activity> adminActivity() {
        List<Activity> activities = activityRepository.findAll();

        return activities;
    }

    @ResponseBody
    @GetMapping("/adminAnswer")
    public List<ActAnswer> adminAnswer() {
        List<ActAnswer> actAnswers = actAnswerRepository.findAll();

        return actAnswers;
    }

    @GetMapping("/admin_delete_activity")
    public String adminDeleteActivity(Long id, HttpSession session) {

        User suser= (User) session.getAttribute("user");
        
        if(suser.getNickname().equals("admin")) {

            activityRepository.deleteById(id);
        }
        return "redirect:/adminmain";
    }

    @GetMapping("/admin_delete_answer")
    public String adminDeleteAnswer(Long id, HttpSession session) {

        User suser= (User) session.getAttribute("user");

        if(suser.getNickname().equals("admin")) {
            
            actAnswerRepository.deleteById(id);
        }


        return "redirect:/adminmain";
    }
    

    

}
