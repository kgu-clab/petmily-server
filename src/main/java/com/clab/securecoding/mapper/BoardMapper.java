package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.BoardRequestDto;
import com.clab.securecoding.type.dto.BoardResponseDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board mapDtoToEntity(BoardRequestDto boardRequestDto, User writer) {
        return Board.builder()
                .writer(writer)
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .build();
    }

    public BoardResponseDto mapEntityToDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();
    }

}
