package com.smhrd.boot.service;

import com.smhrd.boot.entity.Movie;
import com.smhrd.boot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Contioller에서 받은 파라미터를 가공한 후에 repository로 넘기는 역할
@Service
public class MovieService {

    //final(상수)-> 무조건 초기값이 있어야하며 값이 바뀌지 않도록 선언
    //@Autowired : 필드주입방식 (초기화 x)
    // 생성자주입방식 : 직접생성자를 정의하는 방식 / lombok 활용
    // @Autowired
    public MovieRepository repo;

    public MovieService(MovieRepository repo){ 0개의 사용위치
        this.repo = repo;
    }

    // 영화 추가
    public Movie addMovie(Movie movie){
        // 수정
        // 최종 파라미터(movie)를 repository로 넘기기
        Movie result = repo.save(movie); //save(레포지토리로 다루는 클래스 형태의 객체)
        return result;
    }
}
