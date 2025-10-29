package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 메인 페이지 매핑
    @GetMapping("/main_page")
    public String main_page() {
        return "main_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_page")
    public String category_page() {
        return "category_page"; // templates 폴더의 account-notifications.html을 찾음
    }


}
