package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @Column(name = "tool_id")
    private Integer tool_id;

    @Column(name = "tool_name")
    private String tool_name;

    @Column(name = "category_id")
    private Integer category_id;
}
