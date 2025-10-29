package com.smhrd.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // 전체 파라미터가 들어가는 생성자
@NoArgsConstructor // 기본 생성자(파라미터가 아무것도 안들어가는 생성자)
@Getter // 필드에 접근할 수 있는 메서드
@Setter // 필드 값 수정 or 초기화 메서드
@Entity // JPA 사용시 꼭 추가해줘야 하는 *필수* 어노테이션 -> Movie 클래스 형태로 테이블을 생성
@Table(name="") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)
public class Main {
}
