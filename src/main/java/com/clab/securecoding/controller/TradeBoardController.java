package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.TradeBoardService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.TradeBoardRequestDto;
import com.clab.securecoding.type.dto.TradeBoardResponseDto;
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
@RequestMapping("/trade-boards")
@RequiredArgsConstructor
@Tag(name = "Trade Board")
@Slf4j
public class TradeBoardController {

    private final TradeBoardService tradeBoardService;

    @Operation(summary = "거래 게시글 생성", description = "거래 게시글 생성")
    @PostMapping()
    public ResponseModel createTradeBoard(
            @RequestBody TradeBoardRequestDto tradeBoardRequestDto
    ) {
        tradeBoardService.createTradeBoard(tradeBoardRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "거래 게시글 정보", description = "거래 게시글 정보 조회")
    @GetMapping()
    public ResponseModel getTradeBoards() {
        List<TradeBoardResponseDto> tradeBoards = tradeBoardService.getTradeBoards();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(tradeBoards);
        return responseModel;
    }

    @Operation(summary = "거래 게시글 검색", description = "거래 게시글의 ID 또는 제목을 기반으로 검색")
    @GetMapping("/search")
    public ResponseModel searchTradeBoards(
            @RequestParam(required = false) Long tradeBoardId,
            @RequestParam(required = false) String title
    ) {
        List<TradeBoardResponseDto> tradeBoards = tradeBoardService.searchTradeBoards(tradeBoardId, title);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(tradeBoards);
        return responseModel;
    }

    @Operation(summary = "거래 게시글 수정", description = "거래 게시글 수정")
    @PatchMapping("/{tradeBoardId}")
    public ResponseModel updateTradeBoard(
            @PathVariable Long tradeBoardId,
            @RequestBody TradeBoardRequestDto tradeBoardRequestDto
    ) throws PermissionDeniedException {
        tradeBoardService.updateTradeBoard(tradeBoardId, tradeBoardRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "거래 게시글 삭제", description = "거래 게시글 삭제(본인 또는 관리자만 가능)")
    @DeleteMapping("/{tradeBoardId}")
    public ResponseModel deleteTradeBoard(
            @PathVariable Long tradeBoardId
    ) throws PermissionDeniedException {
        tradeBoardService.deleteTradeBoard(tradeBoardId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}