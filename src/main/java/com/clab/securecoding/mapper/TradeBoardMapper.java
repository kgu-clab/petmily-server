package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.TradeBoardRequestDto;
import com.clab.securecoding.type.dto.TradeBoardResponseDto;
import com.clab.securecoding.type.entity.TradeBoard;
import org.springframework.stereotype.Component;

@Component
public class TradeBoardMapper {

    public TradeBoard mapDtoToEntity(TradeBoardRequestDto tradeBoardRequestDto) {
        return TradeBoard.builder()
                .title(tradeBoardRequestDto.getTitle())
                .content(tradeBoardRequestDto.getContent())
                .price(tradeBoardRequestDto.getPrice())
                .location(tradeBoardRequestDto.getLocaiton())
                .build();
    }

    public TradeBoardResponseDto mapEntityToDto(TradeBoard tradeBoard) {
        return TradeBoardResponseDto.builder()
                .id(tradeBoard.getId())
                .seller(tradeBoard.getSeller())
                .title(tradeBoard.getTitle())
                .content(tradeBoard.getContent())
                .price(tradeBoard.getPrice())
                .location(tradeBoard.getLocation())
                .createdAt(tradeBoard.getCreatedAt())
                .build();
    }

}
