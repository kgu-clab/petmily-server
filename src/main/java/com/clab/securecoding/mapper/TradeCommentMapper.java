package com.clab.securecoding.mapper;

import com.clab.securecoding.service.TradeBoardService;
import com.clab.securecoding.type.dto.TradeCommentRequestDto;
import com.clab.securecoding.type.dto.TradeCommentResponseDto;
import com.clab.securecoding.type.entity.TradeBoard;
import com.clab.securecoding.type.entity.TradeComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeCommentMapper {

    private final TradeBoardService tradeBoardService;

    public TradeComment mapDtoToEntity(TradeCommentRequestDto tradeCommentRequestDto) {
        TradeBoard tradeBoard = tradeBoardService.getTradeBoardByIdOrThrow(tradeCommentRequestDto.getTradeBoard());
        return TradeComment.builder()
                .tradeBoard(tradeBoard)
                .content(tradeCommentRequestDto.getContent())
                .build();
    }

    public TradeCommentResponseDto mapEntityToDto(TradeComment tradeComment) {
        return TradeCommentResponseDto.builder()
                .id(tradeComment.getId())
                .tradeBoard(tradeComment.getTradeBoard())
                .writer(tradeComment.getWriter())
                .content(tradeComment.getContent())
                .createdAt(tradeComment.getCreatedAt())
                .build();
    }

}
