package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor // 전체 파라미터가 들어가는 생성자
@Getter // 필드에 접근할 수 있는 메서드
@Setter // 필드 값 수정 or 초기화 메서드
@Entity // JPA 사용시 꼭 추가해줘야 하는 *필수* 어노테이션 -> Movie 클래스 형태로 테이블을 생성
@Table(name="t_movie_sb") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)
public class XXMovie {
    // 영화ID(식별자1~..), 영화제목, 개봉일자, 감독, 누적관객수, 등록일자
    // JAP로 생성되는 테이블은 PK(기본키)가 필수
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1~.. 자동으로 들어감
    private Long movieID;

    @Column(nullable = false, length = 300) // Notnull 옵션, 컬럼크기
    private String title; // ~> title(컬럼이름) varchar(자료형) 255(컬럼 크기)

    private LocalDate releaseDate;

    @Column(name = "m_director") // 컬럼설정
    private String director;

    private int audienceCount;

    @CreationTimestamp // insert 시 자동 현재 시간 입력
    private LocalDateTime createdAt;
}
