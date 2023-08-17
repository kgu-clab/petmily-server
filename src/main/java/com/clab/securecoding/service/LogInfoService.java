package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.LogInfoMapper;
import com.clab.securecoding.repository.LogInfoRepository;
import com.clab.securecoding.type.dto.LogInfoRequestDto;
import com.clab.securecoding.type.dto.LogInfoResponseDto;
import com.clab.securecoding.type.entity.LogInfo;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogInfoService {

    private final LogInfoRepository logInfoRepository;

    private final LogInfoMapper logInfoMapper;

    private final UserService userService;

    public LogInfo createLogInfo(LogInfoRequestDto logInfoRequestDto, HttpServletRequest request) {
        User user = userService.getCurrentUser();
        LogInfo logInfo = LogInfo.builder()
                .logType(logInfoRequestDto.getLogType())
                .id(user.getId())
                .userType(user.getType())
                .ip(request.getRemoteAddr())
                .danger(logInfoRequestDto.getDanger())
                .build();
        logInfoRepository.save(logInfo);
        return logInfo;
    }

    public List<LogInfoResponseDto> getLogInfos() {
        List<LogInfo> logInfos = logInfoRepository.findAll();
        List<LogInfoResponseDto> logInfoResponseDtos = new ArrayList<>();
        for (LogInfo logInfo : logInfos) {
            LogInfoResponseDto logInfoResponseDto = logInfoMapper.mapEntityToDto(logInfo);
            logInfoResponseDtos.add(logInfoResponseDto);
        }
        return logInfoResponseDtos;
    }

    public List<LogInfoResponseDto> searchLogInfos(
            LogType logType,
            String id,
            UserType userType,
            String ip,
            String danger
    ) {
        List<LogInfo> logInfos = new ArrayList<>();
        List<LogInfoResponseDto> logInfoResponseDtos = new ArrayList<>();

        if (logType != null) {
            logInfos = logInfoRepository.findLogInfoByLogType(logType);
        }
        else if (id != null) {
            logInfos = logInfoRepository.findLogInfoById(id);
        }
        else if (userType != null) {
            logInfos = logInfoRepository.findLogInfoByUserType(userType);
        }
        else if (ip != null) {
            logInfos = logInfoRepository.findLogInfoByIp(ip);
        }
        else if (danger != null) {
            logInfos = logInfoRepository.findLogInfoByDanger(danger);
        }
        else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (logInfos.isEmpty()) {
            throw new SearchResultNotExistException();
        }
        else {
        }

        for (LogInfo logInfo : logInfos) {
            LogInfoResponseDto logInfoResponseDto = logInfoMapper.mapEntityToDto(logInfo);
            logInfoResponseDtos.add(logInfoResponseDto);
        }
        return logInfoResponseDtos;
    }

    public void deleteLogInfo(Long logInfoId) throws PermissionDeniedException {
        LogInfo logInfo = getLogInfoByIdOrThrow(logInfoId);
        if (userService.checkUserAdminRole()) {
            logInfoRepository.delete(logInfo);
        }
        else {
            throw new PermissionDeniedException();
        }
    }

    public LogInfo getLogInfoByIdOrThrow(Long logInfoId) {
        return logInfoRepository.findById(logInfoId)
                .orElseThrow(() -> new NotFoundException("해당 로그가 없습니다."));
    }


}