package com.clab.securecoding.service;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.repository.BlacklistIpRepository;
import com.clab.securecoding.type.entity.BlacklistIp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlacklistService {

    private final BlacklistIpRepository blacklistIpRepository;

    private final UserService userService;

    public void addBlacklistedIp(String ipAddress) throws PermissionDeniedException {
        userService.checkUserAdminRole();
        if (!isBlacklistedIp(ipAddress)) {
            BlacklistIp blacklistIp = BlacklistIp.builder().ipAddress(ipAddress).build();
            blacklistIpRepository.save(blacklistIp);
        } else {
        }
    }

    public List<BlacklistIp> getBlacklistedIps() throws PermissionDeniedException {
        userService.checkUserAdminRole();
        List<BlacklistIp> blacklistedIps = blacklistIpRepository.findAll();
        return blacklistedIps;
    }

    @Transactional
    public void deleteBlacklistedIp(String ipAddress) throws PermissionDeniedException {
        userService.checkUserAdminRole();
        if (isBlacklistedIp(ipAddress)) {
            blacklistIpRepository.deleteByIpAddress(ipAddress);
        } else {
        }
    }

    public void clearBlacklist() throws PermissionDeniedException {
        userService.checkUserAdminRole();
        blacklistIpRepository.deleteAll();
    }

    public boolean isBlacklistedIp(String ipAddress) {
        return blacklistIpRepository.existsByIpAddress(ipAddress);
    }

}


