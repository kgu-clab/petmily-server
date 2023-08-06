package com.clab.securecoding.type.dto;

import com.clab.securecoding.type.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BoardRequestDto {

    private User writer;

    private String title;

    private String content;

}
