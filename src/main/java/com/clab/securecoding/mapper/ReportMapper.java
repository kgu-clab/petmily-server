package com.clab.securecoding.mapper;

import com.clab.securecoding.service.BoardService;
import com.clab.securecoding.type.dto.ReportRequestDto;
import com.clab.securecoding.type.dto.ReportResponseDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportMapper {

    private final BoardService boardService;

    public Report mapDtoToEntity(ReportRequestDto reportRequestDto) {
        Board board = boardService.getBoardByIdOrThrow(reportRequestDto.getBoardId());
        return Report.builder()
                .board(board)
                .reportType(reportRequestDto.getReportType())
                .title(reportRequestDto.getTitle())
                .content(reportRequestDto.getContent())
                .build();
    }

    public ReportResponseDto mapEntityToDto(Report report) {
        return ReportResponseDto.builder()
                .id(report.getId())
                .writer(report.getWriter())
                .board(report.getBoard())
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .createdAt(report.getCreatedAt())
                .build();
    }

}
