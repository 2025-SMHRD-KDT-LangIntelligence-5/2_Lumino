package com.smhrd.boot.controller;

import com.smhrd.boot.entity.Movie;
import com.smhrd.boot.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// 응답 결과가 html인 메서드 작성
@RequiredArgsConstructor
@Controller
public class ViewController {

    private final MovieService service;

    // movies.html 응답(첫 페이지 요청 -> [GET]localhost:8089)
    @GetMapping("/")
    public String moviesPage(Model model){
        List<Movie> list = service.getMovieList();
        model.addAttribute("movieList", list);
        //Thymeleaf(.html) : templates 경로를 resources/templates 설정해줌
        return "account-notifications";
    }
}
