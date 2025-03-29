package com.example.ServiceBooking.entity;

import com.example.ServiceBooking.dto.UserDto;
import com.example.ServiceBooking.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String lastName;

    private String phoneNumber;

    private UserRole role;

    public UserDto getDTO() {
        UserDto userDto = new UserDto() ;
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setLastName(lastName);
        userDto.setRole(role);

        return userDto ;
    }
}
