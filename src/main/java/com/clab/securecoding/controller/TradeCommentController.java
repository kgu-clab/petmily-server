package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.TradeCommentService;
import com.clab.securecoding.type.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trade-comments")
@RequiredArgsConstructor
@Tag(name = "Trade Comment")
@Slf4j
public class TradeCommentController {

    private final TradeCommentService tradeCommentService;

    @Operation(summary = "댓글 생성", description = "댓글 생성<br>" +
            "Long tradeBoard;<br>" +
            "String content;")
    @PostMapping("/{tradeBoardId}")
    public ResponseModel createComment(
            @RequestBody TradeCommentRequestDto tradeCommentRequestDto
    ) {
        tradeCommentService.createComment(tradeCommentRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "댓글 조회", description = "댓글 조회")
    @GetMapping("/list/{tradeBoardId}")
    public ResponseModel getCommentsByTradeBoardId(
            @PathVariable Long tradeBoardId
    ) {
        List<TradeCommentResponseDto> comments = tradeCommentService.getCommentsByTradeBoardId(tradeBoardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(comments);
        return responseModel;
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정<br>" +
            "String content;")
    @PatchMapping("/{tradeCommentId}")
    public ResponseModel updateComment(
            @PathVariable Long tradeCommentId,
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto
    ) throws PermissionDeniedException {
        tradeCommentService.updateComment(tradeCommentId, commentUpdateRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제(본인 또는 관리자만 가능)")
    @DeleteMapping("/{tradeCommentId}")
    public ResponseModel deleteTradeComment(
            @PathVariable Long tradeCommentId
    ) throws PermissionDeniedException {
        tradeCommentService.deleteComment(tradeCommentId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
