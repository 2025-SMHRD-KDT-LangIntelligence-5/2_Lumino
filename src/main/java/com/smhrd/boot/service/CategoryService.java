package com.smhrd.boot.service;

import com.smhrd.boot.entity.Tool;
import com.smhrd.boot.repository.CategoryRepository;
import com.smhrd.boot.repository.CommunityRepository;
import com.smhrd.boot.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryrepo;

    @Autowired
    private ToolRepository toolRepository;

    // 카테고리별 도구 조회
    public List<Tool> getToolsByCategory(Integer categoryId) {
        return toolRepository.findByCategoryId(categoryId);
    }
}
