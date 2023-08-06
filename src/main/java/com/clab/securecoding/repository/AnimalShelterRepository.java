package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.AnimalShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalShelterRepository extends JpaRepository<AnimalShelter, Long> {

}
