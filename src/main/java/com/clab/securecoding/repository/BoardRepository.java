package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContainingIgnoreCase(String title);

}
