package com.example.ServiceBooking.services.authentication;

import com.example.ServiceBooking.dto.CompanyUserDto;
import com.example.ServiceBooking.dto.SignUpRequestCompanyDto;
import com.example.ServiceBooking.dto.SignupRequestDto;
import com.example.ServiceBooking.dto.UserDto;
import com.example.ServiceBooking.entity.CompanyUser;
import com.example.ServiceBooking.entity.User;
import com.example.ServiceBooking.enums.UserRole;
import com.example.ServiceBooking.repository.CompanyUserRepo;
import com.example.ServiceBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyUserRepo companyUserRepo;

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

    private Boolean presentByEmailCompany(String email) {
        return companyUserRepo.findFirstByEmail(email) != null ;
    }


    public CompanyUserDto signupCompany(SignUpRequestCompanyDto signUpRequestCompanyDto) {
        CompanyUser companyUser = new CompanyUser() ;

        companyUser.setName(signUpRequestCompanyDto.getName());
        companyUser.setAddress(signUpRequestCompanyDto.getAddress());
        companyUser.setEmail(signUpRequestCompanyDto.getEmail());
        companyUser.setPassword(signUpRequestCompanyDto.getPassword());
        companyUser.setPhoneNumber(signUpRequestCompanyDto.getPhoneNumber());

        companyUser.setRole(UserRole.COMPANY);
        return companyUserRepo.save(companyUser).getcompanyDTO();
    }
}
