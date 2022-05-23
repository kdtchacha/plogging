package com.namoonhee.plogging.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsCrawlingController {

    @GetMapping("/newscontent")
    public List<Map<String, Object>> newsCrawling() {

        List<Map<String, Object>> newslist = new ArrayList<>();

        Connection conn = Jsoup.connect(
                "https://www.google.com/search?q=%ED%94%8C%EB%A1%9C%EA%B9%85&newwindow=1&tbm=nws&source=lnt&tbs=qdr:w&sa=X&ved=2ahUKEwjMpPrlivX3AhWbAogKHX6gAkwQpwV6BAgBEBs&biw=1920&bih=961&dpr=1");

        try {
            Document doc = conn.get();
            Elements eles = doc.select("[style='margin-top:8px;-webkit-line-clamp:3']");

            for (Element ele : eles) {
                System.out.println(ele.text());
                Map<String, Object> map = new HashMap<>();

                map.put("content", ele.text());
                newslist.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newslist;
    }
}
