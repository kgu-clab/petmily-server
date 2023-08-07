package com.clab.securecoding.type.dto;

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
public class TradeBoardResponseDto {

    private Long id;

    private User seller;

    private String title;

    private String content;

    private Long price;

    private String location;

    private LocalDateTime createdAt;

}
