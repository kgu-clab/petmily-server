package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdoptionReportResponseDto {

    private Long id;

    private User writer;

    private AnimalAdoptionBoard animalAdoptionBoard;

    private ReportType reportType;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
