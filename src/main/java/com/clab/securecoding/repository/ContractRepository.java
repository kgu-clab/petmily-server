package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Contract;
import com.clab.securecoding.type.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByUserOrderByCreatedAtDesc(User user);

}
