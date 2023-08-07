package com.clab.securecoding.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeCommentRequestDto {

    private Long tradeBoard;

    private String content;

}
