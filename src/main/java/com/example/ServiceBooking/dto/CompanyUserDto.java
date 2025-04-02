package com.example.ServiceBooking.dto;

import com.example.ServiceBooking.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String Address;

    private String phoneNumber;

    private UserRole role;
}
