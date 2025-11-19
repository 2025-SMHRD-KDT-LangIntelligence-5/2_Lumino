package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // JPA가 엔티티를 생성할 때 필요한 기본 생성자
@AllArgsConstructor // 전체 파라미터가 들어가는 생성자
@Getter // 필드에 접근할 수 있는 메서드
@Setter // 필드 값 수정 or 초기화 메서드
@Entity // JPA 사용시 꼭 추가해줘야 하는 *필수* 어노테이션 -> Movie 클래스 형태로 테이블을 생성
@Table(name="tools") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)
public class Compare {
    @Id // 기본키
    @Column(name = "tool_id")
    private Integer toolId;
    
    @Column(name = "tool_name")
    private String toolName;
    
    @Column(name = "price_info")
    private String priceInfo;

    @Lob
    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "webapp_info")
    private String webappInfo;

    @Column(name = "website_use", length = 1000)
    private String websiteUse;

    @Column(name = "website_detail", length = 1000)
    private String websiteDetail;

    @Column(name = "category_id")
    private Integer categoryId;
}
