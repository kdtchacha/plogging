package com.namoonhee.plogging.controller;

import java.util.List;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PloggingController {
    @RequestMapping("/comeon")
    public String plogging() {
        return "plogging";
    }

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/index")
    public String index(Model model) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");

        Pageable p = PageRequest.of(0, 6, sort);

        List<Activity> publist = activityRepository.findByVisibility(1, p);

        model.addAttribute("publist", publist);

        return "index";
    }

    @GetMapping("maptest")
    public String mapatest() {
        return "maptest";
    }

}