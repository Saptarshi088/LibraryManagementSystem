package com.saptarshi.SecurityPractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Integer id;
    private String username;
    private String roles;

}
