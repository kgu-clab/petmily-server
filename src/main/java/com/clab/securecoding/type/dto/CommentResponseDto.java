package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;

    private Board board;

    private User writer;

    private String content;

    private LocalDateTime createdAt;

}
