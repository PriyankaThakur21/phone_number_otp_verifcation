package com.merchant.phone_number_verifcation.mapper;

import com.merchant.phone_number_verifcation.dto.PhoneOtpRequestDto;
import com.merchant.phone_number_verifcation.model.PhoneOtp;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PhoneOtpMapper {
    PhoneOtpMapper INSTANCE = Mappers.getMapper(PhoneOtpMapper.class);
    PhoneOtpRequestDto mapPhoneOtpToDto(PhoneOtp entity);
    PhoneOtp mapDtoToPhoneOtp(PhoneOtpRequestDto dto);
}
