package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LogInfoRequestDto {

    private LogType logType;

    private String id;

    private UserType userType;

    private String ip;

    private String danger;

}