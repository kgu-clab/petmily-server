package com.clab.securecoding.controller;

import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.ResponseModel;
import com.clab.securecoding.type.dto.UserUpdateRequestDto;
import com.clab.securecoding.type.dto.UserRequestDto;
import com.clab.securecoding.type.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User")
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "신규 유저 생성", description = "신규 유저 생성<br>" +
            "Long id;<br>"+
            "String password;<br>" +
            "String nickname;<br>" +
            "String email;<br>" +
            "String address;<br>" +
            "String contact;<br>" +
            "String type;")
    @PostMapping("/create")
    public ResponseModel createUser(
            @RequestBody UserRequestDto userRequestDto
    ) throws PermissionDeniedException {
        userService.createUser(userRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 정보", description = "프로필 정보 조회")
    @GetMapping("/list")
    public ResponseModel getUsers() throws PermissionDeniedException {
        List<UserResponseDto> users = userService.getUsers();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(users);
        return responseModel;
    }

    @Operation(summary = "유저 검색", description = "유저의 ID 또는 이름을 기반으로 검색")
    @GetMapping("/search")
    public ResponseModel searchUser(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String nickname
    ) throws PermissionDeniedException {
        UserResponseDto user = userService.searchUser(userId, nickname);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(user);
        return responseModel;
    }

    @Operation(summary = "유저 정보 수정", description = "본인 정보 수정<br>" +
            "String password;<br>" +
            "String nickname;<br>" +
            "String email;<br>" +
            "String address;<br>" +
            "String contact;<br>" +
            "String type;")
    @PutMapping("/update")
    public ResponseModel updateUserInfoByUser(
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        userService.updateUserInfoByUser(userUpdateRequestDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 삭제(관리자 전용)", description = "관리자에 의한 유저 삭제(모든 계정 삭제 가능)")
    @DeleteMapping("/delete/{userId}")
    public ResponseModel deleteUserByAdmin(
            @PathVariable("userId") Long userId
    ) throws PermissionDeniedException {
        userService.deleteUserByAdmin(userId);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @Operation(summary = "유저 삭제(일반 유저 전용)", description = "본인 계정 삭제")
    @DeleteMapping("/delete")
    public ResponseModel deleteUserByUser() {
        userService.deleteUserByUser();
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

}
