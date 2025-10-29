package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Main;
import com.smhrd.boot.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Main, Integer> {
}
