package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.LoginFailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginFailInfoRepository extends JpaRepository<LoginFailInfo, Long> {

    LoginFailInfo findByUser_Id(String name);

}
