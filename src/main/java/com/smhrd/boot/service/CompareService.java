package com.smhrd.boot.service;

import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.repository.CompareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareService {
    @Autowired
    private CompareRepository comparerepo;
    
    // tool_name으로 도구 정보 조회
    public Compare getToolByName(String toolName) {
        return comparerepo.findByToolName(toolName);
    }
}
