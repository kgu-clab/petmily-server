package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByUser_Id(Long userId);

    List<Animal> findAllBySpecies(String species);

}
