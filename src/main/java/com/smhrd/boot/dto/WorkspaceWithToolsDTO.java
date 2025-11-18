package com.smhrd.boot.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceWithToolsDTO {
    @Id // 기본키
    @Column(name = "workspace_id")
    private Integer workspaceId;

    @Column(name = "workspace_name")
    private String workspaceName;

    private List<ToolDTO> tools;
}
