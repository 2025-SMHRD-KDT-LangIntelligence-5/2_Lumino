package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor // JPA가 엔티티를 생성할 때 필요한 기본 생성자
@AllArgsConstructor // 전체 파라미터가 들어가는 생성자
@Getter // 필드에 접근할 수 있는 메서드
@Setter // 필드 값 수정 or 초기화 메서드
@Entity // JPA 사용시 꼭 추가해줘야 하는 *필수* 어노테이션 -> Movie 클래스 형태로 테이블을 생성
@Table(name="community_posts") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)
public class Community {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1~.. 자동으로 들어감
    @Column(name = "post_idx")
    private Integer postIdx;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "workspace_id")
    private Integer workspaceId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "likes", columnDefinition = "int default 0")
    private Integer likes = 0;
}
