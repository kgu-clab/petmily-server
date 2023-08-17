package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.mapper.TradeCommentMapper;
import com.clab.securecoding.repository.TradeCommentRepository;
import com.clab.securecoding.type.dto.CommentUpdateRequestDto;
import com.clab.securecoding.type.dto.TradeCommentRequestDto;
import com.clab.securecoding.type.dto.TradeCommentResponseDto;
import com.clab.securecoding.type.entity.TradeBoard;
import com.clab.securecoding.type.entity.TradeComment;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeCommentService {

    private final TradeCommentRepository tradeCommentRepository;
    private final TradeCommentMapper tradeCommentMapper;
    private final TradeBoardService tradeBoardService;
    private final UserService userService;

    public void createComment(TradeCommentRequestDto tradeCommentRequestDto) {
        User writer = userService.getCurrentUser();
        TradeComment comment = tradeCommentMapper.mapDtoToEntity(tradeCommentRequestDto);
        comment.setWriter(writer);
        tradeCommentRepository.save(comment);
    }

    public List<TradeCommentResponseDto> getCommentsByTradeBoardId(Long tradeBoardId) {
        TradeBoard tradeBoard = tradeBoardService.getTradeBoardByIdOrThrow(tradeBoardId);
        List<TradeComment> tradeComments = tradeCommentRepository.findByTradeBoardOrderByCreatedAtAsc(tradeBoard);
        List<TradeCommentResponseDto> TradeCommentResponseDtos = new ArrayList<>();
        for (TradeComment tradeComment : tradeComments) {
            TradeCommentResponseDto tradeCommentResponseDto = tradeCommentMapper.mapEntityToDto(tradeComment);
            TradeCommentResponseDtos.add(tradeCommentResponseDto);
        }
        return TradeCommentResponseDtos;
    }

    public void updateComment(Long tradeCommentId, CommentUpdateRequestDto commentUpdateRequestDto) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        TradeComment comment = getTradeCommentByIdOrThrow(tradeCommentId);
        if (!writer.equals(comment.getWriter()))
            throw new PermissionDeniedException();
        comment.setContent(commentUpdateRequestDto.getContent());
        tradeCommentRepository.save(comment);
    }

    public void deleteComment(Long tradeCommentId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        TradeComment tradeComment = getTradeCommentByIdOrThrow(tradeCommentId);
        if (writer.equals(tradeComment.getWriter()) || userService.checkUserAdminRole())
            tradeCommentRepository.delete(tradeComment);
        else
            throw new PermissionDeniedException();
    }

    private TradeComment getTradeCommentByIdOrThrow(Long tradeCommentId) {
        return tradeCommentRepository.findById(tradeCommentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글이 없습니다."));
    }

}
