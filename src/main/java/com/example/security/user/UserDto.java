package com.example.security.user;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data
    public class UserDto {
        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
        private Collection<? extends GrantedAuthority> permissions;
    }


