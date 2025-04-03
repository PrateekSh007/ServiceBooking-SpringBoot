package com.example.ServiceBooking.controller;

import com.example.ServiceBooking.dto.*;
import com.example.ServiceBooking.entity.User;
import com.example.ServiceBooking.repository.UserRepository;
import com.example.ServiceBooking.services.authentication.AuthService;
import com.example.ServiceBooking.services.jwt.UserDetailsService;
import com.example.ServiceBooking.services.jwt.UserDetailsServiceImpl;
import com.example.ServiceBooking.utils.JwtUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import java.io.IOException;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";


    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Client already exist" , HttpStatus.NOT_ACCEPTABLE) ;
        }

        UserDto createdUser = authService.signupclient(signupRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK) ;
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignUpRequestCompanyDto signUpRequestCompanyDto){
        if(authService.presentByEmail(signUpRequestCompanyDto.getEmail())){
            return new ResponseEntity<>("Company already exist" , HttpStatus.NOT_ACCEPTABLE) ;
        }

        CompanyUserDto createdUser = authService.signupCompany(signUpRequestCompanyDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK) ;
    }

    @PostMapping({"/authenticate"})
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e) ;
        }

//        final UserDetails userDetails = userDetailsService.loadUserbyUsername(authenticationRequest.getUsername()) ;

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());


        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername()) ;

        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getRole())
                .toString()
        ) ;

        response.setContentType("application/json");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

    }
}
