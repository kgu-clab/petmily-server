package com.clab.securecoding.mapper;

import com.clab.securecoding.service.AnimalAdoptionBoardService;
import com.clab.securecoding.type.dto.AdoptionReportRequestDto;
import com.clab.securecoding.type.dto.AdoptionReportResponseDto;
import com.clab.securecoding.type.entity.AdoptionReport;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdoptionReportMapper {

    private final AnimalAdoptionBoardService animalAdoptionBoardService;

    public AdoptionReport mapDtoToEntity(AdoptionReportRequestDto requestDto) {
        AnimalAdoptionBoard animalAdoptionBoard = animalAdoptionBoardService.getAnimalAdoptionBoardByIdOrThrow(requestDto.getAnimalAdoptionBoardId());
        return AdoptionReport.builder()
                .animalAdoptionBoard(animalAdoptionBoard)
                .reportType(requestDto.getReportType())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
    }

    public AdoptionReportResponseDto mapEntityToDto(AdoptionReport adoptionReport) {
        return AdoptionReportResponseDto.builder()
                .id(adoptionReport.getId())
                .writer(adoptionReport.getWriter())
                .animalAdoptionBoard(adoptionReport.getAnimalAdoptionBoard())
                .reportType(adoptionReport.getReportType())
                .title(adoptionReport.getTitle())
                .content(adoptionReport.getContent())
                .createdAt(adoptionReport.getCreatedAt())
                .build();
    }

}
