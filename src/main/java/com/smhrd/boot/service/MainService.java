package com.smhrd.boot.service;

import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.repository.CompareRepository;
import com.smhrd.boot.repository.MainRepository;
import com.smhrd.boot.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
