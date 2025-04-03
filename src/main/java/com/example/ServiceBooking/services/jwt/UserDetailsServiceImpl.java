package com.example.ServiceBooking.services.jwt;


import com.example.ServiceBooking.entity.User;
import com.example.ServiceBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email) ;
        if(user == null) throw new UsernameNotFoundException("Username not found" , null) ;
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayDeque<>()) ;
    }

}
