package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class MainController {

    // 메인 페이지 매핑
    @GetMapping("/index")
    public String index() {
        return "index"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 메인1 페이지 매핑
    @GetMapping("/main1_page")
    public String main1_page() {
        return "main1_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 메인2 페이지 매핑
    @GetMapping("/main2_page")
    public String main2_page() {
        return "main2_page"; // templates 폴더의 account-notifications.html을 찾음
    }
