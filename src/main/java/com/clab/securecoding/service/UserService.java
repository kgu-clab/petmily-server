package com.clab.securecoding.service;

import com.clab.securecoding.auth.util.AuthUtil;
import com.clab.securecoding.exception.*;
import com.clab.securecoding.mapper.UserMapper;
import com.clab.securecoding.repository.*;
import com.clab.securecoding.type.dto.UserUpdateRequestDto;
import com.clab.securecoding.type.dto.UserRequestDto;
import com.clab.securecoding.type.dto.UserResponseDto;
import com.clab.securecoding.type.entity.LoginFailInfo;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.etc.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final LoginFailInfoRepository loginFailInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserRequestDto userRequestDto) {
        if (isExistUserId(userRequestDto.getId()))
            throw new AssociatedAccountExistsException();
        if (isExistContact(userRequestDto.getContact()))
            throw new DuplicateContactException();
        User user = userMapper.mapDtoToEntity(userRequestDto);
        user.setContact(removeHyphensFromContact(user.getContact()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LoginFailInfo loginFailInfo = LoginFailInfo.builder()
                .user(user)
                .loginFailCount(0L)
                .isLock(false)
                .build();
        user.setLoginFailInfo(loginFailInfo);
        loginFailInfoRepository.save(loginFailInfo);
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

    public UserResponseDto searchUser(Long seq, String nickname) throws PermissionDeniedException {
        checkUserAdminRole();
        User user = null;
        if (seq != null)
            user = getUserByIdOrThrow(seq);
        else if (nickname != null)
            user = getUserByNicknameOrThrow(nickname);
        else
            throw new IllegalArgumentException("적어도 seq 또는 name 중 하나를 제공해야 합니다.");

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

    public void deleteUserByAdmin(Long seq) throws PermissionDeniedException {
        checkUserAdminRole();
        getUserByIdOrThrow(seq);
        userRepository.deleteById(seq);
    }

    public void deleteUserByUser() {
        User user = getCurrentUser();
        userRepository.delete(user);
    }

    public boolean checkUserAdminRole() throws PermissionDeniedException {
        User user = getCurrentUser();
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new PermissionDeniedException("권한이 부족합니다.");
        }
        return true;
    }

    public User getUserByIdOrThrow(Long seq) {
        return userRepository.findById(seq)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    public User getUserByNicknameOrThrow(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
    }

    public User getCurrentUser() {
        String userId = AuthUtil.getAuthenticationInfoUserId();
        User user = userRepository.findByUserId(userId).get();
        return user;
    }

    public boolean isExistUserInfo(String userId, String contact) {
        if (userId != null) return isExistUserId(userId);
        else if (contact != null) return isExistContact(contact);
        return false;
    }

    public boolean isExistUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean isExistContact(String contact) {
        return userRepository.existsByContact(contact);
    }

    public String removeHyphensFromContact(String contact) {
        return contact.replaceAll("-", "");
    }

}
