package com.clab.securecoding.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
