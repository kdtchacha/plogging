package com.namoonhee.plogging.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/activityform")
    public String actForm() {

        return "activityform";
    }

    @ResponseBody
    @PostMapping("/act")
    public String actCreate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos) {
        activityService.activityCreate(req, session, photos);

        return "ok";
    }

    // @GetMapping("/camera")
    // public String camera() {

    // return "camera";
    // }

    // @GetMapping("/slide")
    // public String slide() {

    // return "slide";
    // }

    @GetMapping("/activityupdate")
    public String actUpdateForm(Long actid, Model model) {

        Optional<Activity> act = activityService.actUpdateForm(actid);
        model.addAttribute("act", act.get());

        return "activityupdateform";
    }

    @ResponseBody
    @PostMapping("/actupdate")
    public String actUpdate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos) {
        Long actid = Long.decode(req.getParameter("actid"));
        Activity act = activityRepository.findById(actid).get();
        User suser = (User) session.getAttribute("user");

        String res = "";        
        if (act.getUser().getId() == suser.getId()) {
            activityService.activityUpdate(req, session, photos);
            res = "ok";
        }else{
            res = "fail";
        }

        return res;
    }

    @GetMapping(value = "/visibility")
    public String visibility(Long id, HttpSession session) {
        activityService.visibility(id, session);

        return "redirect:/mypage_new";
    }

    @ResponseBody
    @GetMapping("linetest")
    public String lineData(Long actid) {
        String linedata = activityService.lineData(actid);

        return linedata;
    }

    @PostMapping("/actanswer/create")
    public String answercreate(String content, long actid, HttpSession session) {
        activityService.answerCreate(content, actid, session);

        return "redirect:/mapline?actid=" + actid;
    }

    @GetMapping(value = "delanswer")
    public String deleteActivity(@RequestParam Long id, HttpSession session) {
        Long actid = activityService.answerDelete(id, session);

        return "redirect:/mapline?actid=" + actid;
    }

    @ResponseBody
    @PostMapping(value = "actlike")
    public String actLike(HttpSession session, HttpServletRequest req){
        activityService.actLike(session, req);
    
        return "ok";
    }

}
