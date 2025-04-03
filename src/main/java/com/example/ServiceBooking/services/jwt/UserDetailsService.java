package com.example.ServiceBooking.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
//    UserDetails loadUserbyUsername(String username) throws UsernameNotFoundException;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
