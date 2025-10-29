package com.smhrd.boot.controller;

import com.smhrd.boot.entity.CommunityPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    @GetMapping("/account-notifications")
    public String community(CommunityPage community) {
        return "account-notifications"; // templates 폴더의 account-notifications.html을 찾음
    }

    @GetMapping("/auth-login-social")
    public String community1(CommunityPage community) {
        return "auth-login-social"; // templates 폴더의 account-notifications.html을 찾음
    }
}
