package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Report;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAll();

    List<Report> findByWriter(User writer);

    List<Report> findByBoard(Board board);

    List<Report> findByReportType(ReportType reportType);

}