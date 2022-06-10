package com.namoonhee.plogging.controller;

import com.namoonhee.plogging.email.Mailer;
import com.namoonhee.plogging.email.SMTPAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactController {
  @Autowired
  Mailer mailer;
  

  @PostMapping("/contact")
  public String contact(String email, String subject, String content) {
    mailer.sendMail("emdk789@naver.com", // 수신 이메일(관리자)
        "[" + email + "]" + subject, // [작성자 이메일]제목
        content, // 본문
        new SMTPAuthenticator()); // 인증
    return "redirect:/";
  }
}