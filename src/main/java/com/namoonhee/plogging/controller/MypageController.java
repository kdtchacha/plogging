package com.namoonhee.plogging.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MypageController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/mypage")
    public String mypage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        List<Activity> myActList = userService.myActList(user);

        model.addAttribute("list", myActList);

        return "mypage";

    }

    @GetMapping(value = "/deleteaccount")
    public String deleteAccountForm() {
        return "deleteaccount";

    }

    @PostMapping(value = "/deleteaccount")
    public String deleteAccount(@ModelAttribute User user, HttpSession httpSession, Model model) {

        Optional<User> opt = userService.signin(user);

        String redir = "";

        if (opt.isPresent()) {

            userService.deleteAccount(user);
            httpSession.invalidate();
            model.addAttribute("deleteaccount_result", "success");
            redir = "index";

        } else {
            model.addAttribute("deleteaccount_result", "fail");

            redir = "mypage";
        }

        return redir;
    }

}
