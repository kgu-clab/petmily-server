package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.Board;
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
public class ReportResponseDto {

    private Long id;

    private User writer;

    private Board board;

    private ReportType reportType;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
