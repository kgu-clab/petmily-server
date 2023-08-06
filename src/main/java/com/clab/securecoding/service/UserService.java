package com.clab.securecoding.service;

import com.clab.securecoding.auth.util.AuthUtil;
import com.clab.securecoding.exception.AssociatedAccountExistsException;
import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.repository.UserRepository;
import com.clab.securecoding.type.dto.UserUpdateRequestDto;
import com.clab.securecoding.type.dto.UserRequestDto;
import com.clab.securecoding.type.dto.UserResponseDto;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.OAuthProvider;
import com.clab.securecoding.type.etc.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserRequestDto userRequestDto) throws PermissionDeniedException {
        checkUserAdminRole();
        if (userRepository.findById(userRequestDto.getId()).isPresent())
            throw new AssociatedAccountExistsException();
        User user = toUser(userRequestDto);
        userRepository.save(user);
    }

    public List<UserResponseDto> getUsers() throws PermissionDeniedException {
        checkUserAdminRole();
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = toUserResponseDto(user);
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    public UserResponseDto searchUser(Long userId, String nickname) throws PermissionDeniedException {
        checkUserAdminRole();
        User user = null;
        if (userId != null)
            user = getUserByIdOrThrow(userId);
        else if (nickname != null)
            user = getUserByNicknameOrThrow(nickname);
        else
            throw new IllegalArgumentException("적어도 userId 또는 name 중 하나를 제공해야 합니다.");

        if (user == null)
            throw new SearchResultNotExistException("검색 결과가 존재하지 않습니다.");
        return toUserResponseDto(user);
    }

    public void updateUserInfoByUser(UserUpdateRequestDto userUpdateRequestDto) {
        Long userId = AuthUtil.getAuthenticationInfoUserId();
        User user = userRepository.findById(userId).get();
        user.setPassword(userUpdateRequestDto.getPassword());
        user.setNickname(userUpdateRequestDto.getNickname());
        user.setEmail(userUpdateRequestDto.getEmail());
        user.setAddress(userUpdateRequestDto.getAddress());
        user.setContact(userUpdateRequestDto.getContact());
        user.setType(userUpdateRequestDto.getType());
        userRepository.save(user);
    }

    public void deleteUserByAdmin(Long userId) throws PermissionDeniedException {
        checkUserAdminRole();
        getUserByIdOrThrow(userId);
        userRepository.deleteById(userId);
    }

    public void deleteUserByUser() {
        Long userId = AuthUtil.getAuthenticationInfoUserId();
        userRepository.deleteById(userId);
    }

    public void checkUserAdminRole() throws PermissionDeniedException {
        Long userId = AuthUtil.getAuthenticationInfoUserId();
        User user = userRepository.findById(userId).get();
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new PermissionDeniedException("권한이 부족합니다.");
        }
    }

    public User getUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    public User getUserByNicknameOrThrow(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    private User toUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .id(userRequestDto.getId())
                .password(userRequestDto.getPassword())
                .nickname(userRequestDto.getNickname())
                .email(userRequestDto.getEmail())
                .address(userRequestDto.getAddress())
                .contact(userRequestDto.getContact())
                .type(userRequestDto.getType())
                .role(Role.USER)
                .provider(OAuthProvider.LOCAL)
                .build();
        return user;
    }

    private UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .address(user.getAddress())
                .contact(user.getContact())
                .type(user.getType())
                .role(user.getRole())
                .provider(user.getProvider())
                .createdAt(user.getCreatedAt())
                .build();
        return userResponseDto;
    }

}
