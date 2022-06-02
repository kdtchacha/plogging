package com.namoonhee.plogging.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.UserRepository;
import com.namoonhee.plogging.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MypageController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/mypage")
    public String mypage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        List<Activity> myActList = userService.myActList(user);

        model.addAttribute("list", myActList);

        return "mypage";

    }

    @GetMapping(value="/mypage_new")
    public String mypagenew(HttpSession session, Model model) {

        // User user = (User) session.getAttribute("user");

        // List<Activity> myActList = userService.myActList(user);

        // model.addAttribute("list", myActList);

        return "mypage_new";
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

    @GetMapping("/user_info/update")
    public String userInfoUpdateForm() {

        // Optional <User> opt = UserRepository.findById(Long id);
        // model.addAttribute("email", opt.get());
        return "user_info_update";
    }

    @PostMapping("/user_info/update")
    public String userInfoUpdate(HttpSession session, User user) {

        // 세션에 연결된 유저 정보.
        User suser = (User) session.getAttribute("user");

        // 그 정보를 토대로 디비에 접속해서 디비에 저장되어있는 유저 정보 한 줄을 가져옴.
        Optional<User> opt = userRepository.findById(suser.getId());

        // 그 한 줄에서 정보를 빼온다.
        User dbuser = opt.get();

        // 닉네임에 새로운 닉네임과 비밀번호를 세팅한다.
        dbuser.setNickname(user.getNickname());
        dbuser.setPwd(user.getPwd());

        // 그 새로운 닉네임과 비밀번호를 디비에 저장한다.
        userRepository.save(dbuser);


        // 세션에 새로 저장한 닉네임을 세팅해준다.(유저는 화분/디비유저는 심을 내용물)
        session.setAttribute("user", dbuser);

        // System.out.println(suser.getEmail());
        // System.out.println(suser.getPwd());
        // System.out.println(suser.getNickname());
        // System.out.println(suser.getId());

        // userRepository.findById(id);

        // suser 중에서도 nickname을 가져옴.
        // suser.getNickname();

        // String a = user.getNickname();

        // User newNickname = user.getNickname();

        // // Activity dbact = actFromdb.get();

        // newNickname.setNickname();

        // UserRepository.save(newNickname);

        return "mypage";
    }

}
