package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.BlacklistIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistIpRepository extends JpaRepository<BlacklistIp, Long> {

    boolean existsByIpAddress(String ipAddress);

    void deleteByIpAddress(String ipAddress);

}