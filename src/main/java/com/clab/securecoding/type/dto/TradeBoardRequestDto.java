package com.clab.securecoding.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeBoardRequestDto {

    private String title;

    private String content;

    private Long price;

    private String locaiton;

}
