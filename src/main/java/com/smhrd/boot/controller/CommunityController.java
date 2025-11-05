package com.smhrd.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CommunityController {

    // 커뮤니티 페이지 매핑
    @GetMapping("/community_page")
    public String community_page() {
        return "community_page"; // templates 폴더의 account-notifications.html을 찾음
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
