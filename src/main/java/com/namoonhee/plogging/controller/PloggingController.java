package com.namoonhee.plogging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PloggingController {
    @RequestMapping("/comeon")
    public String plogging() {
        return "plogging";
    }



    @GetMapping("/index")
    public String index() {
        return "indextemp";
    }

}