package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller
public class MainController {

    // 메인 페이지 매핑
    @GetMapping("/index")
    public String index() {
        return "index"; // templates 폴더의 index을 찾음
    }

}
