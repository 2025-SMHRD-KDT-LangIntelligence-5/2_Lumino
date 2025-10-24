package com.smhrd.boot.controller;

import com.smhrd.boot.entity.Movie;
import com.smhrd.boot.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

// @Controller : 뷰(jsp파일)를 반환하는 컨트롤러
@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@RestController // 데이터(결과)를 반환하는 컨트롤러
public class MovieController {

    private final MovieService service;

    // 영화 정보 추가(추가하고 싶은 영화정보를 입력 ~~> 요청 데이터로 전송)
    // RestAPI ~> RestfulAPI : RESTAPI 규칙에 맞춰서 설계된 API
    // [Get]localhost:8089/movies?id=1 => 특정 영화정보 조회
    // [Get]localhost:8089/movies?id=1
    // [Get]localhost:8089/movies => 전체 영화 정보 조회
    // [Post]localhost:8089/movies => 영화 정보 추가

//    @PostMapping("/movies")
//    public Movie addMovie(@ModelAttribute Movie movie){
//        // @RequestParam ; 파라미터를 하나하나 받는 방법
//        // @ModelAttribute : 모델(객체) 형태로 묶어서 받는 방법(Movie 객체 형태) -> 어노테이션 생략가능
//        Movie result = service.addMovie(movie);
//
//        return result;
//    }

    @PostMapping("/movies")
    public RedirectView addMovie(@ModelAttribute Movie movie){
        // @RequestParam ; 파라미터를 하나하나 받는 방법
        // @ModelAttribute : 모델(객체) 형태로 묶어서 받는 방법(Movie 객체 형태) -> 어노테이션 생략가능
        Movie result = service.addMovie(movie);

        return new RedirectView("/"); // 리다이렉팅 방식으로 "/" 경로로 다시 요청하게 해서 movies.html 볼 수 있도록 함
    }

    // 전체 영화 정보 조회
    @GetMapping("/movies")
    public List<Movie> getMovieList(){
        List<Movie> list = service.getMovieList();
        return list; // 응답
    }

    // 특정 영화 정보 조회
    @GetMapping("/movies/{movieId}") // {} : 사용자의 입력을 받는 값
    public Movie getMovie(@PathVariable Long movieId){
        Movie movie = service.getMovie(movieId);
        return movie;
    }
}
