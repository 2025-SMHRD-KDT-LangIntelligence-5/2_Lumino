package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CompareController {

    // AI비교 페이지 매핑
    @GetMapping("/compare_page")
    public String compare_page() {
        return "compare_page"; // templates 폴더의 account-notifications.html을 찾음
    }
    // AI비교 페이지 매핑
    @GetMapping("/nav_page")
    public String nav_page() {
        return "nav_page"; // templates 폴더의 account-notifications.html을 찾음
    }

}
