package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@RestController // 데이터(결과)를 반환하는 컨트롤러
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
