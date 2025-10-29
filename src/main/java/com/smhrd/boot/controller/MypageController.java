package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    // 마이 페이지 매핑
    @GetMapping("/my_page")
    public String my_page() {
        return "my_page"; // templates 폴더의 account-notifications.html을 찾음
    }


}
