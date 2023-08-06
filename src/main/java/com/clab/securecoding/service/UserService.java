package com.clab.securecoding.service;

import com.clab.securecoding.auth.util.AuthUtil;
import com.clab.securecoding.exception.AssociatedAccountExistsException;
import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.UserMapper;
import com.clab.securecoding.repository.UserRepository;
import com.clab.securecoding.type.dto.UserUpdateRequestDto;
import com.clab.securecoding.type.dto.UserRequestDto;
import com.clab.securecoding.type.dto.UserResponseDto;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public void createUser(UserRequestDto userRequestDto) throws PermissionDeniedException {
        checkUserAdminRole();
        if (userRepository.findById(userRequestDto.getId()).isPresent())
            throw new AssociatedAccountExistsException();
        User user = userMapper.mapDtoToEntity(userRequestDto);
        userRepository.save(user);
    }

    public List<UserResponseDto> getUsers() throws PermissionDeniedException {
        checkUserAdminRole();
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = userMapper.mapEntityToDto(user);
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
            throw new SearchResultNotExistException();
        return userMapper.mapEntityToDto(user);
    }

    public void updateUserInfoByUser(UserUpdateRequestDto userUpdateRequestDto) {
        User user = getCurrentUser();
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

    public boolean checkUserAdminRole() throws PermissionDeniedException {
        User user = getCurrentUser();
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new PermissionDeniedException("권한이 부족합니다.");
        }
        return true;
    }

    public User getUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    public User getUserByNicknameOrThrow(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    public User getCurrentUser() {
        Long userId = AuthUtil.getAuthenticationInfoUserId();
        User user = userRepository.findById(userId).get();
        return user;
    }

}
