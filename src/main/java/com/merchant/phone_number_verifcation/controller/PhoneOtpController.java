package com.merchant.phone_number_verifcation.controller;

import com.merchant.phone_number_verifcation.dto.PhoneOtpRequestDto;
import com.merchant.phone_number_verifcation.dto.PhoneOtpResponseDto;
import com.merchant.phone_number_verifcation.mapper.PhoneOtpMapper;
import com.merchant.phone_number_verifcation.model.PhoneOtp;
import com.merchant.phone_number_verifcation.service.PhoneOtpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/saas/ui/phoneNumber")
@CrossOrigin(origins = "*")
public class PhoneOtpController {
    @Autowired
    private PhoneOtpServiceImpl phoneOtpService;
    @Autowired
    private PhoneOtpMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(PhoneOtpController.class);

    @PostMapping("/sendOtp")
    public Mono<PhoneOtpResponseDto> sendOtp(@RequestBody PhoneOtpRequestDto phoneOtpDto) {
        logger.info("Inside sendOtp");
        PhoneOtpResponseDto responseDto= new PhoneOtpResponseDto();
        try {
            PhoneOtp phoneOtp = mapper.mapDtoToPhoneOtp(phoneOtpDto);
            String otpSent = phoneOtpService.sendOtp(phoneOtp);

            responseDto.setMessage(otpSent);
            responseDto.setStatus("200");
            responseDto.setSuccess(true);

            return Mono.just(responseDto);
        }
        catch(Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setStatus("400");
            responseDto.setSuccess(false);

            return Mono.just(responseDto);
        }
    }

    @PostMapping("/verifyOtp")
    public Mono<PhoneOtpResponseDto> verifyOTP(@RequestBody PhoneOtpRequestDto phoneOtpDto) {
        logger.info("Inside verifyOtp");
        PhoneOtpResponseDto responseDto= new PhoneOtpResponseDto();
        try {
            PhoneOtp phoneOtp = mapper.mapDtoToPhoneOtp(phoneOtpDto);
            String otpVerified = phoneOtpService.verifyOtp(phoneOtp);
System.out.println(otpVerified);
            responseDto.setMessage(otpVerified);
            responseDto.setStatus("200");
            responseDto.setSuccess(true);

            return Mono.just(responseDto);
        }
        catch(Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setStatus("400");
            responseDto.setSuccess(false);

            return Mono.just(responseDto);
        }
    }
}

