package com.example.ServiceBooking.services.authentication;

import com.example.ServiceBooking.dto.CompanyUserDto;
import com.example.ServiceBooking.dto.SignUpRequestCompanyDto;
import com.example.ServiceBooking.dto.SignupRequestDto;
import com.example.ServiceBooking.dto.UserDto;

public interface AuthService {

    UserDto signupclient(SignupRequestDto signupRequestDto);
    Boolean presentByEmail(String email) ;
    CompanyUserDto signupCompany(SignUpRequestCompanyDto signUpRequestCompanyDto) ;

}
