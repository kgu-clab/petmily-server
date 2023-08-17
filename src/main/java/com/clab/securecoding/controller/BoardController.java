package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.BoardService;
import com.clab.securecoding.type.dto.BoardRequestDto;
import com.clab.securecoding.type.dto.BoardResponseDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Tag(name = "Board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 생성", description = "게시글 생성<br>" +
            "String title;<br>" +
            "String content;")
    @PostMapping()
    public ResponseModel createBoard(
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        boardService.createBoard(boardRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "게시글 정보", description = "게시글 정보 조회")
    @GetMapping()
    public ResponseModel getBoards() {
        List<BoardResponseDto> boards = boardService.getBoards();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(boards);
        return responseModel;
    }

    @Operation(summary = "게시글 검색", description = "게시글의 ID 또는 제목을 기반으로 검색")
    @GetMapping("/search")
    public ResponseModel searchBoards(
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) String title
    ) {
        List<BoardResponseDto> boards = boardService.searchBoards(boardId, title);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(boards);
        return responseModel;
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정<br>" +
            "String title;<br>" +
            "String content;")
    @PatchMapping("/{boardId}")
    public ResponseModel updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequestDto
    ) throws PermissionDeniedException {
        boardService.updateBoard(boardId, boardRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "게시글 삭제", description = "게시글 삭제(본인 또는 관리자만 가능)")
    @DeleteMapping("/{boardId}")
    public ResponseModel deleteBoard(
            @PathVariable Long boardId
    ) throws PermissionDeniedException {
        boardService.deleteBoard(boardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}