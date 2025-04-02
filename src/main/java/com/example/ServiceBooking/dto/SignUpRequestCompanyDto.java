package com.example.ServiceBooking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestCompanyDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String Address;

    private String phoneNumber;

}
