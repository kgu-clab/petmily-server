package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.ReportType;
import lombok.*;

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