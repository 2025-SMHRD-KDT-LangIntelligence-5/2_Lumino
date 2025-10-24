package com.smhrd.boot.service;

import com.smhrd.boot.entity.Movie;
import com.smhrd.boot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Controller 에서 받은 파라미터를 가공한 후에 repository로 넘기는 역할
@Service
public class MovieService {

    // final(상수) -> 무조건 초기값이 있어야 하며 값이 바뀌지 않도록 선언
    // @Autowired : 필드주입방식(초기화x)
    // 생성자주입방식 : 직접 생성자를 정의하는 방식 / lombok 활용
    // @Autowired
    private final MovieRepository repo;

    public MovieService(MovieRepository repo){
        this.repo = repo;
    }

    // 영화 추가
    public Movie addMovie(Movie movie){
        // 수정
        // 최종 파라미터(movie)를 repository로 넘기기
        Movie result = repo.save(movie); // save(래파직토리로 다루는 클래스 형태의 객체)
        // save = insert문의 역할을 함
        return result;
    }

    public List<Movie> getMovieList(){
        List<Movie> list = repo.findAll();
        return list;
    }

    public Movie getMovie(Long movieId){
        // select * from t_movie where movie_id = 2
        // By - where / ById - pk
        Optional<Movie> result = repo.findById(movieId);
        // => Null일 경우를 정상적으로 처리하기 쉬운 상태
        Movie movie = result.get(); // Optional 안에서 실제 Movie 객체 꺼내오기
        return movie;
    }
}
