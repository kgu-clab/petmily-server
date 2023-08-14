package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Animal;
import com.clab.securecoding.type.etc.AnimalType;
import com.clab.securecoding.type.etc.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByUser_Type(UserType userType);

    List<Animal> findAllByAnimalType(AnimalType animalType);

    List<Animal> findAllByUser_TypeAndAnimalType(UserType userType, AnimalType animalType);

}
