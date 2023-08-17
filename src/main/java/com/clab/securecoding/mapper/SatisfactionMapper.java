package com.clab.securecoding.mapper;

import com.clab.securecoding.service.UserService;
import com.clab.securecoding.type.dto.SatisfactionRequestDto;
import com.clab.securecoding.type.dto.SatisfactionResponseDto;
import com.clab.securecoding.type.entity.Satisfaction;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SatisfactionMapper {

    private final UserService userService;

    public Satisfaction mapDtoToEntity(SatisfactionRequestDto requestDto) {
        User writer = userService.getCurrentUser();

        return Satisfaction.builder()
                .writer(writer)
                .q1(requestDto.getQ1())
                .q2(requestDto.getQ2())
                .q3(requestDto.getQ3())
                .q4(requestDto.getQ4())
                .q5(requestDto.getQ5())
                .q6(requestDto.getQ6())
                .q7(requestDto.getQ7())
                .build();
    }

    public SatisfactionResponseDto mapEntityToDto(Satisfaction satisfaction) {
        return SatisfactionResponseDto.builder()
                .id(satisfaction.getId())
                .writer(satisfaction.getWriter())
                .q1(satisfaction.getQ1())
                .q2(satisfaction.getQ2())
                .q3(satisfaction.getQ3())
                .q4(satisfaction.getQ4())
                .q5(satisfaction.getQ5())
                .q6(satisfaction.getQ6())
                .q7(satisfaction.getQ7())
                .createdAt(satisfaction.getCreatedAt())
                .build();
    }

}
