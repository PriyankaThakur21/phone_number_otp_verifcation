package com.merchant.phone_number_verifcation.exception;

public class PhoneOtpException {
    public String timeExceeded(){
        return "Time exceeded";
    }

    public String invalidOtp() {
        return "Invalid Otp";
    }
}
