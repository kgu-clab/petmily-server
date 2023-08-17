package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.AdoptionRequest;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

    boolean existsByUserAndAnimalAdoptionBoard(User user, AnimalAdoptionBoard animalAdoptionBoard);

    List<AdoptionRequest> findAllByAnimalAdoptionBoard_Writer(User user);

    List<AdoptionRequest> findAllByAnimalAdoptionBoard_Id(Long boardId);

    List<AdoptionRequest> findAllByUser(User user);

}
