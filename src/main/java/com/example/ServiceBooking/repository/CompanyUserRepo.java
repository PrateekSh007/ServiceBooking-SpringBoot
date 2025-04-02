package com.example.ServiceBooking.repository;

import com.example.ServiceBooking.dto.CompanyUserDto;
import com.example.ServiceBooking.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepo extends JpaRepository<CompanyUser,Long> {
    CompanyUserDto findFirstByEmail(String email) ;
}
