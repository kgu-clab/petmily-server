package com.clab.securecoding.type.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RequestDto {

    private String recipientPhoneNumber;

    private String content;

}