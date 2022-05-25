package com.namoonhee.plogging.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivityController {
 
    

    @Autowired
    ActivityRepository actRepository;

    @GetMapping("/activityform")
    public String actForm() {
        return "activityform";
    }



    @PostMapping("/activity/start")
    public String activityStart(Activity act, HttpSession session) {

        User user = (User) session.getAttribute("user");

        act.setUser(user);
        
        actRepository.save(act);

        return "redirect:/activityform";
    }


    @PostMapping("/activity/end")
    public String activityEnd() {

        
        return "redirect:/activityform";
    }


    @PostMapping("/activity/confirm")
    public String activityConfirm() {


        return "";
    }

}
