package com.merchant.phone_number_verifcation.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PhoneOtpRequestDto {
    private Long id;
    private String otp;
    private String phoneNumber;
}
