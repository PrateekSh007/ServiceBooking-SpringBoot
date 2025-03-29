package com.example.ServiceBooking.controller;

import com.example.ServiceBooking.dto.SignupRequestDto;
import com.example.ServiceBooking.dto.UserDto;
import com.example.ServiceBooking.services.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Client already exist" , HttpStatus.NOT_ACCEPTABLE) ;
        }

        UserDto createdUser = authService.signupclient(signupRequestDto);  // we can also pass it by builder
        return new ResponseEntity<>(createdUser, HttpStatus.OK) ;
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Company already exist" , HttpStatus.NOT_ACCEPTABLE) ;
        }

        UserDto createdUser = authService.signupclient(signupRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK) ;
    }
}
