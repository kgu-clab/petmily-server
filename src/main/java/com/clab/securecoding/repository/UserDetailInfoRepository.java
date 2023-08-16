package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.entity.UserDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailInfoRepository extends JpaRepository<UserDetailInfo, Long> {

    UserDetailInfo findByUser(User user);
}
