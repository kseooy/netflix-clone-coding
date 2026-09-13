package com.netflix.netflix_clone.dto;

import com.netflix.netflix_clone.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private Role role; // USER 또는 ADMIN
}