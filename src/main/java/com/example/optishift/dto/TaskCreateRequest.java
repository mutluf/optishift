package com.example.optishift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateRequest {
    private String taskName;
    private String taskDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long projectId;
    private Long shiftId;
}
