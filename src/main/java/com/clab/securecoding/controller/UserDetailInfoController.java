package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.UserDetailInfoService;
import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.UserDetailInfoRequestDto;
import com.clab.securecoding.type.dto.UserDetailInfoResponseDto;
import com.clab.securecoding.type.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-detail")
@RequiredArgsConstructor
@Tag(name = "UserDetailInfo")
public class UserDetailInfoController {

    private final UserDetailInfoService userDetailInfoService;

    private final UserService userService;

    @Operation(summary = "설문조사 생성", description = "설문조사 생성")
    @PostMapping()
    public ResponseModel createUserDetailInfo(
            @RequestBody UserDetailInfoRequestDto requestDto
            ){
        userDetailInfoService.createUserDetailInfo(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "설문조사 조회", description = "설문조사 조회")
    @GetMapping()
    public ResponseModel getUserDetailInfos(){
        List<UserDetailInfoResponseDto> userDetailInfoResponseDtos = userDetailInfoService.getUserDetailInfos();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(userDetailInfoResponseDtos);
        return responseModel;
    }

    @Operation(summary = "설문조사 검색", description = "사용자 ID 기반 설문조사 검색")
    @GetMapping("/search")
    public ResponseModel searchUserDetailInfo(
            @RequestParam(required = true) Long userId
    ){
        User user = userService.getUserByIdOrThrow(userId);
        UserDetailInfoResponseDto userDetailInfoResponseDto = userDetailInfoService.searchUserDetailInfo(user);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(userDetailInfoResponseDto);
        return responseModel;
    }

    @Operation(summary = "설문조사 수정", description = "설문조사 수정")
    @PatchMapping("/{userDetailInfoId}")
    public ResponseModel updateUserDetailInfo(
            @PathVariable Long userDetailInfoId,
            @RequestBody UserDetailInfoRequestDto requestDto
    ) throws PermissionDeniedException {
        userDetailInfoService.updateUserDetailInfo(userDetailInfoId, requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
