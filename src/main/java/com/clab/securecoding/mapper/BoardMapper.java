package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.BoardRequestDto;
import com.clab.securecoding.type.dto.BoardResponseDto;
import com.clab.securecoding.type.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board mapDtoToEntity(BoardRequestDto boardRequestDto) {
        return Board.builder()
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
