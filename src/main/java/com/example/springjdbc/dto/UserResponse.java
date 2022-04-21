package com.example.springjdbc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserResponse {

    private String name;

    private String email;

    public UserResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
