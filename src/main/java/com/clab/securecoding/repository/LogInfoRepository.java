package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.LogInfo;
import com.clab.securecoding.type.etc.LogType;
import com.clab.securecoding.type.etc.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogInfoRepository extends JpaRepository<LogInfo, Long> {

    List<LogInfo> findLogInfoByLogType(LogType logType);

    List<LogInfo> findLogInfoById(String id);

    List<LogInfo> findLogInfoByUserType(UserType userType);

    List<LogInfo> findLogInfoByIp(String ip);

    List<LogInfo> findLogInfoByDanger(String danger);

}