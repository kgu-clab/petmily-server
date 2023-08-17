package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.Satisfaction;
import com.clab.securecoding.type.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatisfactionRepository extends JpaRepository<Satisfaction, Long> {

    List<Satisfaction> findByAnimalAdoptionBoard(AnimalAdoptionBoard animalAdoptionBoard);

    List<Satisfaction> findByWriter(User writer);
}
