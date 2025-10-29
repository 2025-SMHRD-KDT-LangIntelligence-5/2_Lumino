package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@RestController // 데이터(결과)를 반환하는 컨트롤러
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
