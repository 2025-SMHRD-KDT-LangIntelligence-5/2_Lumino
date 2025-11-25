package com.smhrd.boot.service;

import com.smhrd.boot.entity.Community;
import com.smhrd.boot.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    /**
     * 커뮤니티 게시물 저장
     */
    @Transactional
    public Community savePost(Community post) {
        return communityRepository.save(post);
    }

    /**
     * 모든 게시물 조회 (최신순)
     */
    public List<Community> getAllPosts() {
        return communityRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * 특정 게시물 조회
     */
    public Community getPostById(Integer postIdx) {
        return communityRepository.findById(postIdx).orElse(null);
    }

    /**
     * 게시물 삭제
     */
    @Transactional
    public void deletePost(Integer postIdx) {
        communityRepository.deleteById(postIdx);
    }
}
