package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.AdoptionReportService;
import com.clab.securecoding.type.dto.*;
import com.clab.securecoding.type.etc.ReportType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/adoption-reports")
@RequiredArgsConstructor
@Tag(name = "AdoptionReport")
public class AdoptionReportController {

    private final AdoptionReportService adoptionReportService;

    @Operation(summary = "분양 게시글 신고 생성", description = "신고 생성<br>" +
            "Long animalAdoptionBoardId;<br>" +
            "ReportType reportType;<br>" +
            "String title;<br>" +
            "String content;")
    @PostMapping()
    public ResponseModel createAdoptionReport(
            @RequestBody AdoptionReportRequestDto requestDto
    ) {
        adoptionReportService.createAdoptionReport(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "분양 게시글 신고 정보", description = "모든 신고 정보 조회")
    @GetMapping()
    public ResponseModel getAdoptionReports() {
        List<AdoptionReportResponseDto> adoptionReports = adoptionReportService.getAdoptionReports();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(adoptionReports);
        return responseModel;
    }

    @Operation(summary = "분양 게시글 신고 검색", description = "작성자의 닉네임, 신고가 작성된 게시글 번호, 신고 타입으로 검색")
    @GetMapping("/search")
    public ResponseModel searchAdoptionReports(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Long adoptionBoardId,
            @RequestParam(required = false) ReportType reportType
    ) {
        List<AdoptionReportResponseDto> adoptionReportResponseDtos = adoptionReportService.searchAdoptionReports(nickname, adoptionBoardId, reportType);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(adoptionReportResponseDtos);
        return responseModel;
    }

    @Operation(summary = "신고 삭제", description = "신고 삭제(관리자만 가능)")
    @DeleteMapping("/{adoptionReportId}")
    public ResponseModel deleteAdoptionReport(
            @PathVariable Long adoptionReportId
    ) throws PermissionDeniedException {
        adoptionReportService.deleteAdoptionReport(adoptionReportId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
