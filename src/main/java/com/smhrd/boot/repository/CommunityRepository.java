package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Community;
import com.smhrd.boot.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
