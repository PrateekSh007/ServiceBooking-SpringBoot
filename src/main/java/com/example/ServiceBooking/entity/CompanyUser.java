package com.example.ServiceBooking.entity;


import com.example.ServiceBooking.dto.CompanyUserDto;
import com.example.ServiceBooking.dto.UserDto;
import com.example.ServiceBooking.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="company")
public class CompanyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String Address;

    private String phoneNumber;

    private UserRole role;

    public CompanyUserDto getcompanyDTO() {
        CompanyUserDto companyUserDto = new CompanyUserDto();
        companyUserDto.setId(id);
        companyUserDto.setName(name);
        companyUserDto.setEmail(email);
        companyUserDto.setAddress(Address) ;
        companyUserDto.setRole(role);

        return  companyUserDto;
    }

}
