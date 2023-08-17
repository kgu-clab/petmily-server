package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.UserRequestDto;
import com.clab.securecoding.type.dto.UserResponseDto;
import com.clab.securecoding.type.dto.UserUpdateRequestDto;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User")
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "신규 유저 생성", description = "신규 유저 생성")
    @PostMapping()
    public ResponseModel createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        userService.createUser(userRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "모든 유저 정보", description = "관리자를 제외한 모든 유저 정보 조회")
    @GetMapping()
    public ResponseModel getUsers() throws PermissionDeniedException {
        List<UserResponseDto> users = userService.getUsers();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(users);
        return responseModel;
    }

    @Operation(summary = "내 프로필 정보", description = "내 프로필 정보 조회")
    @GetMapping("/my-info")
    public ResponseModel getMyInfo() throws PermissionDeniedException {
        UserResponseDto user = userService.getMyInfo();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(user);
        return responseModel;
    }

    @Operation(summary = "유저 검색", description = "유저의 ID 또는 이름을 기반으로 검색")
    @GetMapping("/search")
    public ResponseModel searchUser(
            @RequestParam(required = false) Long seq,
            @RequestParam(required = false) String nickname
    ) throws PermissionDeniedException {
        UserResponseDto user = userService.searchUser(seq, nickname);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(user);
        return responseModel;
    }

    @Operation(summary = "유저 정보 수정", description = "본인 정보 수정")
    @PatchMapping()
    public ResponseModel updateUserInfoByUser(
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        userService.updateUserInfoByUser(userUpdateRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 삭제(관리자 전용)", description = "관리자에 의한 유저 삭제(모든 계정 삭제 가능)")
    @DeleteMapping("/{seq}")
    public ResponseModel deleteUserByAdmin(
            @PathVariable Long seq
    ) throws PermissionDeniedException {
        userService.deleteUserByAdmin(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 삭제(일반 유저 전용)", description = "본인 계정 삭제")
    @DeleteMapping()
    public ResponseModel deleteUserByUser() {
        userService.deleteUserByUser();
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 정보 중복 확인", description = "유저 정보 중복 확인")
    @GetMapping("/dup")
    public ResponseModel checkExistAccount(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String contact
    ) {
        boolean isDuplicate = userService.isExistUserInfo(userId, contact);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(isDuplicate);
        return responseModel;
    }

}
