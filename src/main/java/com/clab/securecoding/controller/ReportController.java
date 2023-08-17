package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.ReportService;
import com.clab.securecoding.type.dto.ReportRequestDto;
import com.clab.securecoding.type.dto.ReportResponseDto;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.etc.ReportType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Report")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "신고 생성", description = "신고 생성<br>" +
            "Long boardId;<br>" +
            "ReportType reportType;<br>" +
            "String title;<br>" +
            "String content;")
    @PostMapping()
    public ResponseModel createReport(
            @RequestBody ReportRequestDto reportRequestDto
    ) {
        reportService.createReport(reportRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "신고 정보", description = "모든 신고 정보 조회")
    @GetMapping()
    public ResponseModel getReports() {
        List<ReportResponseDto> reports = reportService.getReports();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(reports);
        return responseModel;
    }

    @Operation(summary = "신고 검색", description = "작성자의 닉네임, 신고가 작성된 게시글 번호, 신고 타입으로 검색")
    @PostMapping("/search")
    public ResponseModel searchReports(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) ReportType reportType
    ) {
        List<ReportResponseDto> reports = reportService.searchReports(nickname, boardId, reportType);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(reports);
        return responseModel;
    }

    @Operation(summary = "신고 삭제", description = "신고 삭제(관리자만 가능)")
    @DeleteMapping("/{reportId}")
    public ResponseModel deleteReport(
            @PathVariable Long reportId
    ) throws PermissionDeniedException {
        reportService.deleteReport(reportId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
