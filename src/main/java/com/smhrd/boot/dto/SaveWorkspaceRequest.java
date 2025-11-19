package com.smhrd.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveWorkspaceRequest {
    private String workspaceName;
    private List<String> toolNames;
}
