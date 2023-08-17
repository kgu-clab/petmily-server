package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.LogInfoRequestDto;
import com.clab.securecoding.type.dto.LogInfoResponseDto;
import com.clab.securecoding.type.entity.LogInfo;
import org.springframework.stereotype.Component;

@Component
public class LogInfoMapper {

    public LogInfo mapDtoToEntity(LogInfoRequestDto requestDto) {
        return LogInfo.builder().build();
    }

    public LogInfoResponseDto mapEntityToDto(LogInfo logInfo) {
        return LogInfoResponseDto.builder()
                .seq(logInfo.getSeq())
                .logType(logInfo.getLogType())
                .id(logInfo.getId())
                .userType(logInfo.getUserType())
                .ip(logInfo.getIp())
                .danger(logInfo.getDanger())
                .createdAt(logInfo.getCreatedAt())
                .build();
    }
}