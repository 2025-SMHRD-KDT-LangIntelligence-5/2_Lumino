package com.smhrd.boot.service;

import com.smhrd.boot.entity.XXMovie;
import com.smhrd.boot.repository.XXMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Controller 에서 받은 파라미터를 가공한 후에 repository로 넘기는 역할
@Service
public class XXMovieService {

    // final(상수) -> 무조건 초기값이 있어야 하며 값이 바뀌지 않도록 선언
    // @Autowired : 필드주입방식(초기화x)
    // 생성자주입방식 : 직접 생성자를 정의하는 방식 / lombok 활용
    // @Autowired
    private final XXMovieRepository repo;

    public XXMovieService(XXMovieRepository repo){
        this.repo = repo;
    }

    // 영화 추가
    public XXMovie addMovie(XXMovie movie){
        // 수정
        // 최종 파라미터(movie)를 repository로 넘기기
        XXMovie result = repo.save(movie); // save(래파직토리로 다루는 클래스 형태의 객체)
        // save = insert문의 역할을 함
        return result;
    }

    public List<XXMovie> getMovieList(){
        List<XXMovie> list = repo.findAll();
        return list;
    }

    public XXMovie getMovie(Long movieId){
        // select * from t_movie where movie_id = 2
        // By - where / ById - pk
        Optional<XXMovie> result = repo.findById(movieId);
        // => Null일 경우를 정상적으로 처리하기 쉬운 상태
        XXMovie movie = result.get(); // Optional 안에서 실제 Movie 객체 꺼내오기
        return movie;
    }
}
