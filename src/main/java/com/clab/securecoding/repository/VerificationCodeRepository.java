package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    void deleteByRecipientPhoneNumber(String recipientPhoneNumber);

    VerificationCode findByRecipientPhoneNumber(String recipientPhoneNumber);

}
