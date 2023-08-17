package com.clab.securecoding.type.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SatisfactionRequestDto {

    private String q1;

    private String q2;

    private String q3;

    private String q4;

    private String q5;

    private String q6;

    private String q7;

}
