package com.clab.securecoding.mapper;

import com.clab.securecoding.service.BoardService;
import com.clab.securecoding.type.dto.CommentRequestDto;
import com.clab.securecoding.type.dto.CommentResponseDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final BoardService boardService;

    public Comment mapDtoToEntity(CommentRequestDto commentRequestDto) {
        Board board = boardService.getBoardByIdOrThrow(commentRequestDto.getBoard());
        return Comment.builder()
                .board(board)
                .content(commentRequestDto.getContent())
                .build();
    }

    public CommentResponseDto mapEntityToDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .board(comment.getBoard())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
