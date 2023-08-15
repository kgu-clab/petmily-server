package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.AdoptionReportMapper;
import com.clab.securecoding.repository.AdoptionReportRepository;
import com.clab.securecoding.type.dto.AdoptionReportRequestDto;
import com.clab.securecoding.type.dto.AdoptionReportResponseDto;
import com.clab.securecoding.type.entity.*;
import com.clab.securecoding.type.etc.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionReportService {

    private final AdoptionReportRepository adoptionReportRepository;

    private final AdoptionReportMapper adoptionReportMapper;

    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    private final UserService userService;

    public void createAdoptionReport(AdoptionReportRequestDto requestDto) {
        User writer = userService.getCurrentUser();
        AdoptionReport adoptionReport = adoptionReportMapper.mapDtoToEntity(requestDto);
        adoptionReport.setWriter(writer);
        adoptionReportRepository.save(adoptionReport);
    }

    public List<AdoptionReportResponseDto> getAdoptionReports() {
        List<AdoptionReport> adoptionReports = adoptionReportRepository.findAll();
        List<AdoptionReportResponseDto> adoptionReportResponseDtos = new ArrayList<>();
        for (AdoptionReport adoptionReport : adoptionReports) {
            AdoptionReportResponseDto adoptionReportResponseDto = adoptionReportMapper.mapEntityToDto(adoptionReport);
            adoptionReportResponseDtos.add(adoptionReportResponseDto);
        }
        return adoptionReportResponseDtos;
    }

    public List<AdoptionReportResponseDto> searchAdoptionReports(String nickname, Long adoptionBoardId, ReportType reportType) {
        List<AdoptionReport> adoptionReports = new ArrayList<>();
        List<AdoptionReportResponseDto> adoptionReportResponseDtos = new ArrayList<>();

        if (nickname != null) {
            User writer = userService.getUserByNicknameOrThrow(nickname);
            adoptionReports = adoptionReportRepository.findByWriter(writer);
        } else if (adoptionBoardId != null) {
            AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardService.getAnimalAdoptionBoardByIdOrThrow(adoptionBoardId);
            adoptionReports = adoptionReportRepository.findByAnimalAdoptionBoard(animalAdoptionBoard);
        } else if (reportType != null) {
            adoptionReports = adoptionReportRepository.findByReportType(reportType);
        } else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (adoptionReports.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        for (AdoptionReport adoptionReport : adoptionReports) {
            AdoptionReportResponseDto adoptionReportResponseDto = adoptionReportMapper.mapEntityToDto(adoptionReport);
            adoptionReportResponseDtos.add(adoptionReportResponseDto);
        }
        return adoptionReportResponseDtos;
    }

    public void deleteAdoptionReport(Long adoptionReportId) throws PermissionDeniedException {

        AdoptionReport adoptionReport = getAdoptionReportByIdOrThrow(adoptionReportId);

        if (userService.checkUserAdminRole()) {
            adoptionReportRepository.delete(adoptionReport);
        } else {
            throw new PermissionDeniedException();
        }
    }

    public AdoptionReport getAdoptionReportByIdOrThrow(Long reportId) {
        return adoptionReportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("해당하는 신고가 없습니다."));
    }
}
