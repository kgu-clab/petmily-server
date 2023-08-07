package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Board board);

    List<Comment> findByBoardOrderByCreatedAtAsc(Board board);

}
