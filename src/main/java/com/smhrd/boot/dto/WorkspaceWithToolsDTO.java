package com.smhrd.boot.dto;

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
    private Integer workspaceId;
    private String workspaceName;
    private List<ToolDTO> tools;
}
