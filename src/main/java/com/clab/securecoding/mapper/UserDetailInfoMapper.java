package com.clab.securecoding.mapper;

import com.clab.securecoding.type.dto.UserDetailInfoRequestDto;
import com.clab.securecoding.type.dto.UserDetailInfoResponseDto;
import com.clab.securecoding.type.entity.UserDetailInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailInfoMapper {

    public UserDetailInfo mapDtoToEntity(UserDetailInfoRequestDto requestDto) {

        return UserDetailInfo.builder()
                .timeWithPets(requestDto.getTimeWithPets())
                .environment(requestDto.getEnvironment())
                .numberOfFamily(requestDto.getNumberOfFamily())
                .hasAllergy(requestDto.getHasAllergy())
                .hospitalExpensesForPets(requestDto.getHospitalExpensesForPets())
                .numberOfWalks(requestDto.getNumberOfWalks())
                .hasAlreadyPet(requestDto.getHasAlreadyPet())
                .hasExperienceRaisingPets(requestDto.getHasExperienceRaisingPets())
                .sizeOfHouse(requestDto.getSizeOfHouse())
                .job(requestDto.getJob())
                .age(requestDto.getAge())
                .build();
    }

    public UserDetailInfoResponseDto mapEntityToDto(UserDetailInfo userDetailInfo) {
        log.info("UserDetailInfoMapper.mapEntityToDto");
        return UserDetailInfoResponseDto.builder()
                .id(userDetailInfo.getId())
                .user(userDetailInfo.getUser())
                .timeWithPets(userDetailInfo.getTimeWithPets())
                .environment(userDetailInfo.getEnvironment())
                .numberOfFamily(userDetailInfo.getNumberOfFamily())
                .hasAllergy(userDetailInfo.getHasAllergy())
                .hospitalExpensesForPets(userDetailInfo.getHospitalExpensesForPets())
                .numberOfWalks(userDetailInfo.getNumberOfWalks())
                .hasAlreadyPet(userDetailInfo.getHasAlreadyPet())
                .hasExperienceRaisingPets(userDetailInfo.getHasExperienceRaisingPets())
                .sizeOfHouse(userDetailInfo.getSizeOfHouse())
                .job(userDetailInfo.getJob())
                .age(userDetailInfo.getAge())
                .build();
    }
}
