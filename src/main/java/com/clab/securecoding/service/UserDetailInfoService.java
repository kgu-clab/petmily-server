package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.UserDetailInfoMapper;
import com.clab.securecoding.repository.UserDetailInfoRepository;
import com.clab.securecoding.type.dto.UserDetailInfoRequestDto;
import com.clab.securecoding.type.dto.UserDetailInfoResponseDto;
import com.clab.securecoding.type.entity.User;
import com.clab.securecoding.type.entity.UserDetailInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailInfoService {

    private final UserDetailInfoRepository userDetailInfoRepository;

    private final UserDetailInfoMapper userDetailInfoMapper;

    private final UserService userService;

    public void createUserDetailInfo(UserDetailInfoRequestDto requestDto) {
        User user = userService.getCurrentUser();
        UserDetailInfo userDetailInfo = userDetailInfoMapper.mapDtoToEntity(requestDto);
        userDetailInfo.setUser(user);

        userDetailInfoRepository.save(userDetailInfo);
    }

    public List<UserDetailInfoResponseDto> getUserDetailInfos(){
        log.info("UserDetailInfoService.getUserDetailInfos start");
        List<UserDetailInfo> userDetailInfos = userDetailInfoRepository.findAll();
        log.info("UserDetailInfoService.getUserDetailInfos findAll End");
        List<UserDetailInfoResponseDto> userDetailInfoResponseDtos = new ArrayList<>();
        for (UserDetailInfo userDetailInfo : userDetailInfos) {
            UserDetailInfoResponseDto userDetailInfoResponseDto = userDetailInfoMapper.mapEntityToDto(userDetailInfo);
            userDetailInfoResponseDtos.add(userDetailInfoResponseDto);
        }
        log.info("UserDetailInfoService.getUserDetailInfos end");
        return userDetailInfoResponseDtos;
    }

    public UserDetailInfoResponseDto searchUserDetailInfo(User user){
        UserDetailInfo userDetailInfo;
        if ( user != null) {
            userDetailInfo = userDetailInfoRepository.findByUser(user);
        } else {
            throw new IllegalArgumentException("검색을 위해 값을 입력해주세요.");
        }

        if (userDetailInfo == null) {
            throw new SearchResultNotExistException();
        }

        UserDetailInfoResponseDto userDetailInfoResponseDto = userDetailInfoMapper.mapEntityToDto(userDetailInfo);
        return userDetailInfoResponseDto;
    }

    public void updateUserDetailInfo(Long userDetailInfoId, UserDetailInfoRequestDto userDetailInfoRequestDto) throws PermissionDeniedException {
        User user = userService.getCurrentUser();
        UserDetailInfo userDetailInfo = getUserDetailInfoByIdOrThrow(userDetailInfoId);

        if ( user != userDetailInfo.getUser()){
            throw new PermissionDeniedException();
        }

        setUserDetailInfo(userDetailInfoRequestDto, userDetailInfo);
        userDetailInfoRepository.save(userDetailInfo);
    }

    public UserDetailInfo getUserDetailInfoByIdOrThrow(Long userDetailInfoId){
        return userDetailInfoRepository.findById(userDetailInfoId)
                .orElseThrow(() -> new NotFoundException("해당하는 유저 상세 정보가 없습니다."));
    }

    private void setUserDetailInfo(UserDetailInfoRequestDto userDetailInfoRequestDto, UserDetailInfo userDetailInfo) {
        userDetailInfo.setTimeWithPets(userDetailInfoRequestDto.getTimeWithPets());
        userDetailInfo.setEnvironment(userDetailInfoRequestDto.getEnvironment());
        userDetailInfo.setNumberOfFamily(userDetailInfoRequestDto.getNumberOfFamily());
        userDetailInfo.setHasAllergy(userDetailInfoRequestDto.getHasAllergy());
        userDetailInfo.setHospitalExpensesForPets(userDetailInfoRequestDto.getHospitalExpensesForPets());
        userDetailInfo.setNumberOfWalks(userDetailInfoRequestDto.getNumberOfWalks());
        userDetailInfo.setHasAlreadyPet(userDetailInfoRequestDto.getHasAlreadyPet());
        userDetailInfo.setHasExperienceRaisingPets(userDetailInfoRequestDto.getHasExperienceRaisingPets());
        userDetailInfo.setSizeOfHouse(userDetailInfoRequestDto.getSizeOfHouse());
        userDetailInfo.setJob(userDetailInfoRequestDto.getJob());
        userDetailInfo.setAge(userDetailInfoRequestDto.getAge());
    }

}
