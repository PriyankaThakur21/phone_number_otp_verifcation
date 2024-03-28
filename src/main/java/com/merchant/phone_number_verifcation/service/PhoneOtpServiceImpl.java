package com.merchant.phone_number_verifcation.service;

import com.merchant.phone_number_verifcation.exception.PhoneOtpException;
import com.merchant.phone_number_verifcation.model.PhoneOtp;
import com.merchant.phone_number_verifcation.repository.PhoneOtpRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PhoneOtpServiceImpl {
    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Autowired
    private PhoneOtpRepository phoneOtpRepository;
    @Autowired
    private PhoneOtpException exception;
    private static final Logger logger = LoggerFactory.getLogger(PhoneOtpServiceImpl.class);
    public String generateOtp(int length) {
        try {
            String characters = "0123456789";
            StringBuilder otp = new StringBuilder(length);
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                otp.append(randomChar);
            }
            return otp.toString();
        }
        catch(Exception e){
            return "Exception in generating otp"+e;
        }
    }

    public String sendOtp(PhoneOtp phoneNumberOtp) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        logger.info("Inside send Otp "+ phoneNumberOtp.getPhoneNumber());

        try {
            PhoneOtp otpPresent = phoneOtpRepository.findByPhoneNumber(phoneNumberOtp.getPhoneNumber());
            PhoneOtp phoneOtp = new PhoneOtp();
            if (otpPresent != null) {
                phoneOtp.setId(otpPresent.getId());
            }
            String otp = generateOtp(4);

            phoneOtp.setOtp(otp);
            phoneOtp.setPhoneNumber(phoneNumberOtp.getPhoneNumber());
            phoneOtp.setCreatedAt(LocalDateTime.now());
            phoneOtpRepository.save(phoneOtp);

            String message = "Your PERKQI Verification OTP is  " + otp;
            Message.creator(
                            new com.twilio.type.PhoneNumber(phoneNumberOtp.getPhoneNumber()),
                            new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                            message)
                    .create();
            return "Otp sent successfully";
        } catch (Exception e) {
            logger.info("Exception in sending sms "+ e);
            throw new RuntimeException("Failed to send otp: "+ e.getMessage());
        }
    }

    public String verifyOtp(PhoneOtp phoneOtp) {
        try {
            PhoneOtp otpPresent = phoneOtpRepository.findByPhoneNumber(phoneOtp.getPhoneNumber());
            if (otpPresent.getOtp() == null || !otpPresent.getOtp().equals(phoneOtp.getOtp())) throw new RuntimeException(exception.invalidOtp());
            LocalDateTime otpEnteredTime = LocalDateTime.now();
            Duration duration = Duration.between(otpPresent.getCreatedAt(), otpEnteredTime);
            if (duration.toSeconds() <= 60){
                return "Otp Verified";
            }
            throw new RuntimeException(exception.timeExceeded());
        }
        catch(Exception e){
            logger.info("Exception in verifying otp "+ e);
            throw new RuntimeException("Failed to verify Otp: "+ e.getMessage());
        }
    }
}
