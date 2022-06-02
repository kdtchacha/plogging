package com.namoonhee.plogging.crawlling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test_news {
    @GetMapping("/test/news")
    public String test() {
        Connection conn 
        = Jsoup.connect("https://search.naver.com/search.naver?where=news&sm=tab_jum&query=%ED%94%8C%EB%A1%9C%EA%B9%85");
        try {
            Document doc = conn.get();
            Elements eles = doc.select("a.news_tit");
    
            for(Element ele : eles) {
                System.out.println(ele.text());
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
}


