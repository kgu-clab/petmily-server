package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LogInfoResponseDto {

    private Long seq;

    private LogType logType;

    private String id;

    private UserType userType;

    private String ip;

    private String danger;

    private LocalDateTime createdAt;

}
