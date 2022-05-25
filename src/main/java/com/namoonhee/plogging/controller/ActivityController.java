package com.namoonhee.plogging.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivityController {
    
    @Autowired
    ActivityRepository actRepository;

    @PostMapping("/activity/create")
    public String activityCreate(Activity act, HttpSession session) {

        User user = (User) session.getAttribute("user");

        act.setUser(user);
        act.setCreateDate(new Date());
        actRepository.save(act);

        return "indextemp";
    }

}
