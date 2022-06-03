package com.namoonhee.plogging.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

  @GetMapping("/navernews")
  public Map getNaver(String query) {
    RestTemplate rt = new RestTemplate();
    RequestEntity requestEntity = null;
    try {
      requestEntity = RequestEntity
          .get(
              new URI(
                  "https://openapi.naver.com/v1/search/news.json?display=6&query=" +
                      URLEncoder.encode(query, "utf-8")))
          .header("X-Naver-Client-Id", "luTWH2TgUj6WT_cIta1i")
          .header("X-Naver-Client-Secret", "DilYy6o8IQ")
          .build();

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);

    return entity.getBody();
  }

}
