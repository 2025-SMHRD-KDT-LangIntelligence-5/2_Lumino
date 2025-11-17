package com.smhrd.boot.controller;

import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.service.CompareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CompareController {

    private final CompareService compareService;

    // AI비교 페이지 매핑
    @GetMapping("/compare_page")
    public String compare_page(@RequestParam(required = false) String tool1, 
                              @RequestParam(required = false) String tool2,
                              Model model) {
        try {
            // tool1과 tool2가 모두 제공된 경우에만 DB에서 데이터 조회
            if (tool1 != null && !tool1.isEmpty() && tool2 != null && !tool2.isEmpty()) {
                Compare tool1Data = compareService.getToolByName(tool1);
                Compare tool2Data = compareService.getToolByName(tool2);
                
                // null 체크 후 Model에 추가
                if (tool1Data != null) {
                    model.addAttribute("tool1", tool1Data);
                } else {
                    model.addAttribute("tool1Error", "도구 '" + tool1 + "'를 찾을 수 없습니다.");
                }
                
                if (tool2Data != null) {
                    model.addAttribute("tool2", tool2Data);
                } else {
                    model.addAttribute("tool2Error", "도구 '" + tool2 + "'를 찾을 수 없습니다.");
                }
            }
        } catch (Exception e) {
            // 에러 발생 시 로그 출력 및 에러 메시지 전달
            e.printStackTrace();
            model.addAttribute("error", "데이터를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
        }
        
        return "compare_page"; // templates 폴더의 compare_page.html을 찾음
    }
    
    // AI비교 네비게이션 페이지 매핑
    @GetMapping("/nav_page")
    public String nav_page() {
        return "nav_page"; // templates 폴더의 nav_page.html을 찾음
    }

}
