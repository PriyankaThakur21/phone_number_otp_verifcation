package com.merchant.phone_number_verifcation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneOtpResponseDto {
    private String status;
    private String message;
    private Boolean success;
}
