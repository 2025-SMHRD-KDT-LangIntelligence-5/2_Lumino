package com.smhrd.boot.service;

import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.repository.CompareRepository;
import com.smhrd.boot.repository.MainRepository;
import com.smhrd.boot.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {
    @Autowired
    private MainRepository mainrepo;
    
    @Autowired
    private CompareRepository compareRepository;
    
    // tool_name으로 검색
    public List<Compare> searchTools(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return compareRepository.findAll();
        }
        return compareRepository.findByToolNameContaining(keyword.trim());
    }

    // 고정된 15개 도구 조회 (랜덤 아님)
    public List<Compare> getRandom15Tools() {
        List<Compare> allTools = compareRepository.findAll();
        return allTools.stream().limit(15).collect(Collectors.toList());
    }

    // 모든 도구 조회
    public List<Compare> getAllTools() {
        return compareRepository.findAll();
    }
}
