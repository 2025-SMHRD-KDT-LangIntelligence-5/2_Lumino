package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CategoryController {

    // 카테고리 페이지 매핑
    @GetMapping("/category_all")
    public String category_all() {
        return "category_all"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_audio")
    public String category_audio() {
        return "category_audio"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_coding")
    public String category_coding() {
        return "category_coding"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_education")
    public String category_education() {
        return "category_education"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_image")
    public String category_image() {
        return "category_image"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_search")
    public String category_search() {
        return "category_search"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_text")
    public String category_text() {
        return "category_text"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_video")
    public String category_video() {
        return "category_video"; // templates 폴더의 account-notifications.html을 찾음
    }


}
