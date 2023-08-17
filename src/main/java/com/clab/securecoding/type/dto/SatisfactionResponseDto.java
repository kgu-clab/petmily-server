package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SatisfactionResponseDto {

    private Long id;

    private User writer;

    private String q1;

    private String q2;

    private String q3;

    private String q4;

    private String q5;

    private String q6;

    private String q7;

    private LocalDateTime createdAt;

}
