package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private Board board;

    private String content;

}
