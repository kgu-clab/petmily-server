package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AdoptionReportRequestDto {

    private Long animalAdoptionBoardId;

    private ReportType reportType;

    private String title;

    private String content;

}