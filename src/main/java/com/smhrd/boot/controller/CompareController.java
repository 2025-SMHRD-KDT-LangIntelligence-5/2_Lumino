package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareController {

    // AI비교1 페이지 매핑
    @GetMapping("/compare1_page")
    public String compare1_page() {
        return "compare1_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // AI비교2 페이지 매핑
    @GetMapping("/compare2_page")
    public String compare2_page() {
        return "compare2_page"; // templates 폴더의 account-notifications.html을 찾음
    }


}
