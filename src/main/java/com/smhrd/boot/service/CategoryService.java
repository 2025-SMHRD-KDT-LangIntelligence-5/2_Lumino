package com.smhrd.boot.service;

import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.entity.FixedTools;
import com.smhrd.boot.entity.Tool;
import com.smhrd.boot.repository.CategoryRepository;
import com.smhrd.boot.repository.CompareRepository;
import com.smhrd.boot.repository.FixedToolsRepository;
import com.smhrd.boot.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryrepo;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private CompareRepository compareRepository;

    @Autowired
    private FixedToolsRepository fixedToolsRepository;

    // 카테고리별 도구 조회
    public List<Tool> getToolsByCategory(Integer categoryId) {
        return toolRepository.findByCategoryId(categoryId);
    }

    // 모든 도구 조회
    public List<Compare> getAllTools() {
        return compareRepository.findAll();
    }

    // 랜덤으로 15개 도구 조회 (고정)
    // 처음 호출 시 랜덤하게 선택하여 저장하고, 이후에는 저장된 15개를 반환
    @Transactional
    public List<Compare> getRandom15Tools() {
        // 고정된 도구가 이미 있는지 확인
        if (fixedToolsRepository.count() > 0) {
            // 저장된 고정 도구가 있으면 그것을 반환
            List<FixedTools> fixedTools = fixedToolsRepository.findAllOrderedByDisplayOrder();
            List<Integer> toolIds = fixedTools.stream()
                    .map(FixedTools::getToolId)
                    .collect(Collectors.toList());
            
            // tool_id 목록으로 Compare 엔티티 조회 (순서 유지)
            return toolIds.stream()
                    .map(toolId -> compareRepository.findById(toolId).orElse(null))
                    .filter(tool -> tool != null)
                    .collect(Collectors.toList());
        } else {
            // 고정된 도구가 없으면 랜덤하게 15개 선택
            List<Compare> randomTools = compareRepository.findRandom15Tools();
            
            // 선택된 도구들을 fixed_tools 테이블에 저장
            for (int i = 0; i < randomTools.size(); i++) {
                FixedTools fixedTool = new FixedTools();
                fixedTool.setToolId(randomTools.get(i).getToolId());
                fixedTool.setDisplayOrder(i);
                fixedToolsRepository.save(fixedTool);
            }
            
            return randomTools;
        }
    }
}
