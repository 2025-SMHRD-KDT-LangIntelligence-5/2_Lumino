package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Category;
import com.smhrd.boot.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
