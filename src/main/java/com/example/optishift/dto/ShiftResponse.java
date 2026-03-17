package com.example.optishift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftResponse {
    private Long id;
    private String shiftName;
    private String shiftDescription;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration duration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
