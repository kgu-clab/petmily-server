package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.ReportType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReportRequestDto {

    private Long boardId;

    private ReportType reportType;

    private String title;

    private String content;

}