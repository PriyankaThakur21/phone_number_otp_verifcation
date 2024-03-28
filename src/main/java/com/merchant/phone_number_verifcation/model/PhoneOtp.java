package com.merchant.phone_number_verifcation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PhoneOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "otp")
    private String otp;

    @Column(name = "created_on")
    private LocalDateTime createdAt;

    @Column(name = "phone_number")
    private String phoneNumber;
}
