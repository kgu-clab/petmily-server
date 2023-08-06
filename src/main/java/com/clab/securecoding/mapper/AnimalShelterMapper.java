package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.AnimalShelterDto;
import com.clab.securecoding.type.entity.AnimalShelter;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AnimalShelterMapper {

    public AnimalShelter mapDtoToEntity(AnimalShelterDto dto) {
        return AnimalShelter.builder()
                .careNm(dto.getCareNm())
                .orgNm(dto.getOrgNm())
                .divisionNm(dto.getDivisionNm())
                .saveTrgtAnimal(dto.getSaveTrgtAnimal())
                .careAddr(dto.getCareAddr())
                .jibunAddr(dto.getJibunAddr())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .dsignationDate(dto.getDsignationDate())
                .weekOprStime(dto.getWeekOprStime())
                .weekOprEtime(dto.getWeekOprEtime())
                .weekCellStime(dto.getWeekCellStime())
                .weekCellEtime(dto.getWeekCellEtime())
                .weekendOprStime(dto.getWeekendOprStime())
                .weekendOprEtime(dto.getWeekendOprEtime())
                .weekendCellStime(dto.getWeekendCellStime())
                .weekendCellEtime(dto.getWeekendCellEtime())
                .closeDay(dto.getCloseDay())
                .vetPersonCnt(dto.getVetPersonCnt())
                .specsPersonCnt(dto.getSpecsPersonCnt())
                .medicalCnt(dto.getMedicalCnt())
                .breedCnt(dto.getBreedCnt())
                .quarabtineCnt(dto.getQuarabtineCnt())
                .feedCnt(dto.getFeedCnt())
                .transCarCnt(dto.getTransCarCnt())
                .careTel(dto.getCareTel())
                .dataStdDt(dto.getDataStdDt())
                .build();
    }

    public AnimalShelterDto mapEntityToDto(AnimalShelter entity) {
        return AnimalShelterDto.builder()
                .careNm(entity.getCareNm())
                .orgNm(entity.getOrgNm())
                .divisionNm(entity.getDivisionNm())
                .saveTrgtAnimal(entity.getSaveTrgtAnimal())
                .careAddr(entity.getCareAddr())
                .jibunAddr(entity.getJibunAddr())
                .lat(entity.getLat())
                .lng(entity.getLng())
                .dsignationDate(entity.getDsignationDate())
                .weekOprStime(entity.getWeekOprStime())
                .weekOprEtime(entity.getWeekOprEtime())
                .weekCellStime(entity.getWeekCellStime())
                .weekCellEtime(entity.getWeekCellEtime())
                .weekendOprStime(entity.getWeekendOprStime())
                .weekendOprEtime(entity.getWeekendOprEtime())
                .weekendCellStime(entity.getWeekendCellStime())
                .weekendCellEtime(entity.getWeekendCellEtime())
                .closeDay(entity.getCloseDay())
                .vetPersonCnt(entity.getVetPersonCnt())
                .specsPersonCnt(entity.getSpecsPersonCnt())
                .medicalCnt(entity.getMedicalCnt())
                .breedCnt(entity.getBreedCnt())
                .quarabtineCnt(entity.getQuarabtineCnt())
                .feedCnt(entity.getFeedCnt())
                .transCarCnt(entity.getTransCarCnt())
                .careTel(entity.getCareTel())
                .dataStdDt(entity.getDataStdDt())
                .build();
    }

    public AnimalShelterDto mapJsonToDto(JSONObject item) {
        AnimalShelterDto dto = new AnimalShelterDto();
        dto.setCareNm((String) item.get("careNm"));
        dto.setOrgNm((String) item.get("orgNm"));
        dto.setDivisionNm((String) item.get("divisionNm"));
        dto.setSaveTrgtAnimal((String) item.get("saveTrgtAnimal"));
        dto.setCareAddr((String) item.get("careAddr"));
        dto.setJibunAddr((String) item.get("jibunAddr"));
        dto.setLat((double) item.get("lat"));
        dto.setLng((double) item.get("lng"));
        dto.setDsignationDate((String) item.get("dsignationDate"));
        dto.setWeekOprStime((String) item.get("weekOprStime"));
        dto.setWeekOprEtime((String) item.get("weekOprEtime"));
        dto.setWeekCellStime((String) item.get("weekCellStime"));
        dto.setWeekCellEtime((String) item.get("weekCellEtime"));
        dto.setWeekendOprStime((String) item.get("weekendOprStime"));
        dto.setWeekendOprEtime((String) item.get("weekendOprEtime"));
        dto.setWeekendCellStime((String) item.get("weekendCellStime"));
        dto.setWeekendCellEtime((String) item.get("weekendCellEtime"));
        dto.setCloseDay((String) item.get("closeDay"));
        dto.setVetPersonCnt(((Long) item.get("vetPersonCnt")).intValue());
        dto.setSpecsPersonCnt(((Long) item.get("specsPersonCnt")).intValue());
        dto.setMedicalCnt(((Long) item.get("medicalCnt")).intValue());
        dto.setBreedCnt(((Long) item.get("breedCnt")).intValue());
        dto.setQuarabtineCnt(((Long) item.get("quarabtineCnt")).intValue());
        dto.setFeedCnt(((Long) item.get("feedCnt")).intValue());
        dto.setTransCarCnt(((Long) item.get("transCarCnt")).intValue());
        dto.setCareTel((String) item.get("careTel"));
        dto.setDataStdDt((String) item.get("dataStdDt"));
        return dto;
    }

}
