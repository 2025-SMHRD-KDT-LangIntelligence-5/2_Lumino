package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor // 전체 파라미터가 들어가는 생성자
@NoArgsConstructor  // 기본 생성자(파라미터가 아무것도 안 들어가는 생성자)
@Getter             // 필드에 접근할 수 있는 메서드
@Setter             // 필드 값 수정 or 초기화 메서드
@Entity             //JPA 사용시 꼭 추가해줘야하는(필수) 어노테이션 -> Movie클래스 형태로 테이블을 생성
@Table(name="t_movie") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)

public class Movie {
    // 영화ID(식별자1~ ..), 영화제목, 개봉일자, 감독, 누적관객수, 등록일자
    // JPA로 생성되는 테이블은 PK(기본키)가 필수

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 1~ 자동적으로 계단식 번호 부여

    @Column(nullable = false, length = 300)  //NotNull, 컬럼크기
    private Long movieId;

    private String title; // ~title(컬럼이름), verchar(자료혈), 255(컬럼 크기)

    private LocalDate releaseDate;

    @Column(name ="m_director") // 컬럼 설정
    private String director;

    private int audienceCount;

    @CreationTimestamp //insert시 자동 현재 시간 입력
    private LocalTime createdAt;
}
