package com.merchant.phone_number_verifcation;

import com.merchant.phone_number_verifcation.exception.PhoneOtpException;
import com.merchant.phone_number_verifcation.model.PhoneOtp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneNumberVerifcationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneNumberVerifcationApplication.class, args);
	}

	@Bean
	PhoneOtpException phoneOtpException(){return new PhoneOtpException();}

}
