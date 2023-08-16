package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.UserDetailInfoService;
import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.UserDetailInfoRequestDto;
import com.clab.securecoding.type.dto.UserDetailInfoResponseDto;
import com.clab.securecoding.type.entity.User;
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

    @PostMapping("/create")
    public ResponseModel createUserDetailInfo(
            @RequestBody UserDetailInfoRequestDto requestDto
            ){
        userDetailInfoService.createUserDetailInfo(requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @GetMapping("/list")
    public ResponseModel getUserDetailInfos(){
        List<UserDetailInfoResponseDto> userDetailInfoResponseDtos = userDetailInfoService.getUserDetailInfos();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(userDetailInfoResponseDtos);
        return responseModel;
    }

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

    @PatchMapping("/update/{userDetailInfoId}")
    public ResponseModel updateUserDetailInfo(
            @PathVariable Long userDetailInfoId,
            @RequestBody UserDetailInfoRequestDto requestDto
    ) throws PermissionDeniedException {
        userDetailInfoService.updateUserDetailInfo(userDetailInfoId, requestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
