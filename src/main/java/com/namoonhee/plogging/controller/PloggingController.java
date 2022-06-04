package com.namoonhee.plogging.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PloggingController {
    @RequestMapping("/comeon")
    public String plogging() {
        return "plogging";
    }

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActFileRepository actFileRepository;

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

    @GetMapping("mapline")
    public String mapline() {
        return "mapline";
    }

    @GetMapping(value = "/nawarapic")
    public ResponseEntity<Resource> download(@ModelAttribute Activity activity) throws Exception {
        System.out.println("====================================");
        System.out.println("====================================");
        System.out.println(activity);
        System.out.println("====================================");
        System.out.println("====================================");
        List<ActFile> fList = actFileRepository.findByActivity(activity);

        String fileName = fList.get(0).getSaveFileName();
        File file = new File("c:/project/" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
                .contentLength(file.length())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}