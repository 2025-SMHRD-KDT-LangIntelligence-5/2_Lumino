package com.smhrd.boot.repository;

import com.smhrd.boot.entity.XXMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                    //<테이블로 만들어지는 객체(class)의 이름, class에 지정된 @ID의 타입>
public interface XXMovieRepository extends JpaRepository<XXMovie, Long> {
}
