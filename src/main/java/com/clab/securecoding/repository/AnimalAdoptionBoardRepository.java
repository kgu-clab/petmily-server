package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.etc.AnimalType;
import com.clab.securecoding.type.etc.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalAdoptionBoardRepository extends JpaRepository<AnimalAdoptionBoard, Long> {

    List<AnimalAdoptionBoard> findByAnimalType(AnimalType animalType);

    List<AnimalAdoptionBoard> findBySpecies(String species);

    List<AnimalAdoptionBoard> findByGender(String gender);

    List<AnimalAdoptionBoard> findByAge(Long age);

    List<AnimalAdoptionBoard> findByWriter_Type(UserType userType);

    List<AnimalAdoptionBoard> findByWriter_Nickname(String nickname);
}
