package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.AdoptionReport;
import com.clab.securecoding.type.entity.AnimalAdoptionBoard;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionReportRepository extends JpaRepository<AdoptionReport, Long> {

    List<AdoptionReport> findAll();

    List<AdoptionReport> findByWriter(User writer);

    List<AdoptionReport> findByAnimalAdoptionBoard(AnimalAdoptionBoard animalAdoptionBoard);

    List<AdoptionReport> findByReportType(ReportType reportType);

}
