package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="workspace_items")
public class WorkspaceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workspace_item_id;

    @Column(name = "workspace_id")
    private Integer workspaceId;

    @Column(name = "tool_id")
    private Integer toolId;
}
