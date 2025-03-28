package com.example.ServiceBooking.dto;

import com.example.ServiceBooking.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String lastName;

    private String phoneNumber;

}
