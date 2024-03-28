package com.merchant.phone_number_verifcation.repository;

import com.merchant.phone_number_verifcation.model.PhoneOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneOtpRepository extends JpaRepository<PhoneOtp, Long> {
    PhoneOtp findByPhoneNumber(String phoneNumber);
}
