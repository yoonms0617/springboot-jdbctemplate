package com.example.springjdbc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserList {

    private List<UserResponse> userResponseList;

    public UserList(List<UserResponse> userResponseList) {
        this.userResponseList = userResponseList;
    }

}
