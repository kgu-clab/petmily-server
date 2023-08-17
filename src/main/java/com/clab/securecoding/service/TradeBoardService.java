package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.TradeBoardMapper;
import com.clab.securecoding.repository.TradeBoardRepository;
import com.clab.securecoding.repository.TradeCommentRepository;
import com.clab.securecoding.type.dto.TradeBoardRequestDto;
import com.clab.securecoding.type.dto.TradeBoardResponseDto;
import com.clab.securecoding.type.entity.TradeBoard;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeBoardService {

    private final TradeBoardRepository tradeBoardRepository;

    private final TradeBoardMapper tradeBoardMapper;

    private final UserService userService;

    private final TradeCommentRepository tradeCommentRepository;

    public void createTradeBoard(TradeBoardRequestDto tradeBoardRequestDto) {
        User seller = userService.getCurrentUser();
        TradeBoard tradeBoard = tradeBoardMapper.mapDtoToEntity(tradeBoardRequestDto);
        tradeBoard.setSeller(seller);
        tradeBoardRepository.save(tradeBoard);
    }

    public List<TradeBoardResponseDto> getTradeBoards() {
        List<TradeBoard> tradeBoards = tradeBoardRepository.findAll();
        List<TradeBoardResponseDto> tradeBoardResponseDtos = new ArrayList<>();
        for (TradeBoard tradeBoard : tradeBoards) {
            TradeBoardResponseDto tradeBoardResponseDto = tradeBoardMapper.mapEntityToDto(tradeBoard);
            tradeBoardResponseDtos.add(tradeBoardResponseDto);
        }
        return tradeBoardResponseDtos;
    }

    public List<TradeBoardResponseDto> searchTradeBoards(Long tradeBoardId, String title) {
        List<TradeBoard> tradeBoards = new ArrayList<>();
        if (tradeBoardId != null) {
            TradeBoard tradeBoard = getTradeBoardByIdOrThrow(tradeBoardId);
            tradeBoards.add(tradeBoard);
        } else if (title != null) {
            tradeBoards = tradeBoardRepository.findByTitleContainingIgnoreCase(title);
        } else {
            throw new IllegalArgumentException("적어도 tradeBoardId 또는 title 중 하나를 제공해야 합니다.");
        }

        if (tradeBoards.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        List<TradeBoardResponseDto> tradeBoardResponseDtos = new ArrayList<>();
        for (TradeBoard tradeBoard : tradeBoards) {
            TradeBoardResponseDto tradeBoardResponseDto = tradeBoardMapper.mapEntityToDto(tradeBoard);
            tradeBoardResponseDtos.add(tradeBoardResponseDto);
        }
        return tradeBoardResponseDtos;
    }

    public void updateTradeBoard(Long tradeBoardId, TradeBoardRequestDto tradeBoardRequestDto) throws PermissionDeniedException {
        User seller = userService.getCurrentUser();
        TradeBoard tradeBoard = getTradeBoardByIdOrThrow(tradeBoardId);
        if (seller != tradeBoard.getSeller()) {
            throw new PermissionDeniedException();
        }
        tradeBoard.setTitle(tradeBoardRequestDto.getTitle());
        tradeBoard.setContent(tradeBoardRequestDto.getContent());
        tradeBoard.setPrice(tradeBoardRequestDto.getPrice());
        tradeBoard.setLocation(tradeBoardRequestDto.getLocaiton());
        tradeBoardRepository.save(tradeBoard);
    }

    public void deleteTradeBoard(Long tradeBoardId) throws PermissionDeniedException {
        User seller = userService.getCurrentUser();
        TradeBoard tradeBoard = getTradeBoardByIdOrThrow(tradeBoardId);
        if (seller == tradeBoard.getSeller() || userService.checkUserAdminRole()) {
            tradeBoardRepository.delete(tradeBoard);
        }
        else {
            throw new PermissionDeniedException();
        }
    }

    public TradeBoard getTradeBoardByIdOrThrow(Long tradeBoardId) {
        return tradeBoardRepository.findById(tradeBoardId)
                .orElseThrow(() -> new NotFoundException("해당 거래 게시글이 없습니다."));
    }

}
