package com.namoonhee.plogging.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityController {

    @Autowired
    ActivityRepository actRepository;

    @GetMapping("/activityform")
    public String actForm() {
        return "activityform";
    }

    @ResponseBody
    @PostMapping("/act")
    public String actTimeTest(HttpServletRequest req, HttpSession session) {

        String aa = req.getParameter("hour");
        String a = req.getParameter("min");
        String b = req.getParameter("sec");
        String dist = req.getParameter("dist");
        String actname = req.getParameter("actname");

        System.out.println(a);
        System.out.println(b);

        String c = aa+":"+a+":"+b;
        dist += "m";
        System.out.println(c);

        Activity act = new Activity();
        act.setActTime(c);
        act.setActDistance(dist);
        act.setActName(actname);

        User user = (User) session.getAttribute("user");

        act.setUser(user);

        act.setCreateDate(new Date());

        actRepository.save(act);

        return "aaa";
    }

    @GetMapping("/camera")
    public String camera() {
        return "camera";
    }

}
