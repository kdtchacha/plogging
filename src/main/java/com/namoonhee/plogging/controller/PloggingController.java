package com.namoonhee.plogging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PloggingController {
    @RequestMapping("/comeon")
    public String plogging() {
        return "plogging";
    }
    
}
