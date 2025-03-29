package com.example.ServiceBooking.services.authentication;

import com.example.ServiceBooking.dto.SignupRequestDto;
import com.example.ServiceBooking.dto.UserDto;
import com.example.ServiceBooking.entity.User;
import com.example.ServiceBooking.enums.UserRole;
import com.example.ServiceBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupclient(SignupRequestDto signupRequestDto) {
        User user = new User() ;
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(signupRequestDto.getPassword());
        user.setLastName(signupRequestDto.getLastName());
        user.setPhoneNumber(signupRequestDto.getPhoneNumber());

        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDTO() ;
    }

    public Boolean presentByEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto) {
        User user = new User() ;
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(signupRequestDto.getPassword());
        user.setPhoneNumber(signupRequestDto.getPhoneNumber());

        user.setRole(UserRole.COMPANY);
        return userRepository.save(user).getDTO() ;
    }
}
