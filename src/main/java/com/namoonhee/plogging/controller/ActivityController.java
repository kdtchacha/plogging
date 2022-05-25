package com.namoonhee.plogging.controller;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivityController {
    
    @PostMapping("/activity/create")
    public String activityCreate(Activity act, HttpSession session) {

        

        return "indextemp";
    }

}
