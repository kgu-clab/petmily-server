package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.ReportMapper;
import com.clab.securecoding.repository.ReportRepository;
import com.clab.securecoding.type.dto.ReportRequestDto;
import com.clab.securecoding.type.dto.ReportResponseDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Report;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    private final BoardService boardService;

    private final UserService userService;

    public void createReport(ReportRequestDto reportRequestDto) {
        User writer = userService.getCurrentUser();
        Report report = reportMapper.mapDtoToEntity(reportRequestDto);
        report.setWriter(writer);
        reportRepository.save(report);
    }

    public List<ReportResponseDto> getReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportResponseDto> reportResponseDtos = new ArrayList<>();
        for (Report report : reports) {
            ReportResponseDto reportResponseDto = reportMapper.mapEntityToDto(report);
            reportResponseDtos.add(reportResponseDto);
        }
        return reportResponseDtos;
    }

    public List<ReportResponseDto> searchReports(String nickname, Long boardId, ReportType reportType) {
        List<Report> reports = new ArrayList<>();
        List<ReportResponseDto> reportResponseDtos = new ArrayList<>();

        if (nickname != null) {
            User writer = userService.getUserByNicknameOrThrow(nickname);
            reports = reportRepository.findByWriter(writer);
        }
        else if (boardId != null) {
            Board board = boardService.getBoardByIdOrThrow(boardId);
            reports = reportRepository.findByBoard(board);
        }
        else if (reportType != null) {
            reports = reportRepository.findByReportType(reportType);
        }
        else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (reports.isEmpty()) {
            throw new SearchResultNotExistException();
        }
        else {
        }

        for (Report report : reports) {
            ReportResponseDto reportResponseDto = reportMapper.mapEntityToDto(report);
            reportResponseDtos.add(reportResponseDto);
        }
        return reportResponseDtos;
    }

    public void deleteReport(Long reportId) throws PermissionDeniedException {
        Report report = getReportByIdOrThrow(reportId);
        if (userService.checkUserAdminRole()) {
            reportRepository.delete(report);
        }
        else {
            throw new PermissionDeniedException();
        }
    }

    public Report getReportByIdOrThrow(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("해당하는 신고가 없습니다."));
    }
}
