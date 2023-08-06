package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.CommentRequestDto;
import com.clab.securecoding.type.dto.CommentResponseDto;
import com.clab.securecoding.type.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment mapDtoToEntity(CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .board(commentRequestDto.getBoard())
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
