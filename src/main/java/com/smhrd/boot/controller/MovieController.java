package com.smhrd.boot.controller;

import com.smhrd.boot.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 뷰(jsp파일)를 반환하는 컨트롤러
@RequiredArgsConstructor
@RestController // 데이터(결과)를 반환하는 컨트롤러
public class MovieController {

    private final MovieService service;

    // public MovieController(MovieService service){
    //      this.service = service;
    // }

    // 영화 정보 추가(추가하고 싶은 영화정보를 입력 ~~> 요청 데이터로 전송)
    public  Movie  addMovie(@ModelAttribute Movie movie){
        //@RequestParam : 파라미터를 하나하나 받는 방법
        //@ModelAttribute : 모델(객체)형태로 묶어서 받는 방법(Movie 객체 형태)
        Movie result = service. addMovie(movie);

        return result;
    }
}
