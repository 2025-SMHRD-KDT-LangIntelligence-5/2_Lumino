package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login_JoinController {

    // 회원가입 페이지 매핑
    @GetMapping("/join_page")
    public String join_page() {
        return "join_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 로그인 페이지 매핑
    @GetMapping("/login_page")
    public String login_page() {
        return "login_page"; // templates 폴더의 account-notifications.html을 찾음
    }


}
