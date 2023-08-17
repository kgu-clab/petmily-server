package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.CommentService;
import com.clab.securecoding.type.dto.CommentRequestDto;
import com.clab.securecoding.type.dto.CommentResponseDto;
import com.clab.securecoding.type.dto.CommentUpdateRequestDto;
import com.clab.securecoding.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "Comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글 생성")
    @PostMapping("/{boardId}")
    public ResponseModel createComment(
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentService.createComment(commentRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "댓글 조회", description = "댓글 조회")
    @GetMapping("/list/{boardId}")
    public ResponseModel getCommentsByBoardId(
            @PathVariable Long boardId
    ) {
        List<CommentResponseDto> comments = commentService.getCommentsByBoardId(boardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(comments);
        return responseModel;
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정")
    @PatchMapping("/{commentId}")
    public ResponseModel updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto
    ) throws PermissionDeniedException {
        commentService.updateComment(commentId, commentUpdateRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제(본인 또는 관리자만 가능)")
    @DeleteMapping("/{commentId}")
    public ResponseModel deleteComment(
            @PathVariable Long commentId
    ) throws PermissionDeniedException {
        commentService.deleteComment(commentId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
