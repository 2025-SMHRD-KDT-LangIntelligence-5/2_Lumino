package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    // 커뮤니티1 페이지 매핑
    @GetMapping("/community1_page")
    public String community1_page() {
        return "community1_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 커뮤니티2 페이지 매핑
    @GetMapping("/community2_page")
    public String community2_page() {
        return "community2_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 글쓰기 작성 페이지 매핑
    @GetMapping("/write_page")
    public String write_page() {
        return "write_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 글쓰기 수정 페이지 매핑
    @GetMapping("/edit_page")
    public String edit_page() {
        return "edit_page"; // templates 폴더의 account-notifications.html을 찾음
    }


}
