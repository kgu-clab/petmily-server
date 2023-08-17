package com.clab.securecoding.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalShelterDto {

    private String careNm;

    private String orgNm;

    private String divisionNm;

    private String saveTrgtAnimal;

    private String careAddr;

    private String jibunAddr;

    private double lat;

    private double lng;

    private String dsignationDate;

    private String weekOprStime;

    private String weekOprEtime;

    private String weekCellStime;

    private String weekCellEtime;

    private String weekendOprStime;

    private String weekendOprEtime;

    private String weekendCellStime;

    private String weekendCellEtime;

    private String closeDay;

    private int vetPersonCnt;

    private int specsPersonCnt;

    private int medicalCnt;

    private int breedCnt;

    private int quarabtineCnt;

    private int feedCnt;

    private int transCarCnt;

    private String careTel;

    private String dataStdDt;

}
