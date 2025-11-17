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
@Entity
@Table(name="workspaces") // 생성되는 테이블의 이름 지정(생략하면 클래스 이름과 동일하게 생성)
//@Table(name="workspaces_item")
//@OneToMany

public class Workspace {
    @Id // 기본키
    private Integer workspace_id;
}
