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
        if (item.containsKey("careNm")) {
            dto.setCareNm((String) item.get("careNm"));
        }
        else {
        }
        if (item.containsKey("orgNm")) {
            dto.setOrgNm((String) item.get("orgNm"));
        }
        else {
        }
        if (item.containsKey("divisionNm")) {
            dto.setDivisionNm((String) item.get("divisionNm"));
        }
        else {
        }
        if (item.containsKey("saveTrgtAnimal")) {
            dto.setSaveTrgtAnimal((String) item.get("saveTrgtAnimal"));
        }
        else {
        }
        if (item.containsKey("careAddr")) {
            dto.setCareAddr((String) item.get("careAddr"));
        }
        else {
        }
        if (item.containsKey("jibunAddr")) {
            dto.setJibunAddr((String) item.get("jibunAddr"));
        }
        else {
        }
        if (item.containsKey("lat")) {
            dto.setLat((double) item.get("lat"));
        }
        else {
        }
        if (item.containsKey("lng")) {
            dto.setLng((double) item.get("lng"));
        }
        else {
        }
        if (item.containsKey("dsignationDate")) {
            dto.setDsignationDate((String) item.get("dsignationDate"));
        }
        else {
        }
        if (item.containsKey("weekOprStime")) {
            dto.setWeekOprStime((String) item.get("weekOprStime"));
        }
        else {
        }
        if (item.containsKey("weekOprEtime")) {
            dto.setWeekOprEtime((String) item.get("weekOprEtime"));
        }
        else {
        }
        if (item.containsKey("weekCellStime")) {
            dto.setWeekCellStime((String) item.get("weekCellStime"));
        }
        else {
        }
        if (item.containsKey("weekCellEtime")) {
            dto.setWeekCellEtime((String) item.get("weekCellEtime"));
        }
        else {
        }
        if (item.containsKey("weekendOprStime")) {
            dto.setWeekendOprStime((String) item.get("weekendOprStime"));
        }
        else {
        }
        if (item.containsKey("weekendOprEtime")) {
            dto.setWeekendOprEtime((String) item.get("weekendOprEtime"));
        }
        else {
        }
        if (item.containsKey("weekendCellStime")) {
            dto.setWeekendCellStime((String) item.get("weekendCellStime"));
        }
        else {
        }
        if (item.containsKey("weekendCellEtime")) {
            dto.setWeekendCellEtime((String) item.get("weekendCellEtime"));
        }
        else {
        }
        if (item.containsKey("closeDay")) {
            dto.setCloseDay((String) item.get("closeDay"));
        }
        else {
        }
        if (item.containsKey("vetPersonCnt")) {
            dto.setVetPersonCnt(((Long) item.get("vetPersonCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("specsPersonCnt")) {
            dto.setSpecsPersonCnt(((Long) item.get("specsPersonCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("medicalCnt")) {
            dto.setMedicalCnt(((Long) item.get("medicalCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("breedCnt")) {
            dto.setBreedCnt(((Long) item.get("breedCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("quarabtineCnt")) {
            dto.setQuarabtineCnt(((Long) item.get("quarabtineCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("feedCnt")) {
            dto.setFeedCnt(((Long) item.get("feedCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("transCarCnt")) {
            dto.setTransCarCnt(((Long) item.get("transCarCnt")).intValue());
        }
        else {
        }
        if (item.containsKey("careTel")) {
            dto.setCareTel((String) item.get("careTel"));
        }
        else {
        }
        if (item.containsKey("dataStdDt")) {
            dto.setDataStdDt((String) item.get("dataStdDt"));
        }
        else {
        }
        return dto;
    }


}
