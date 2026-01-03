package com.saptarshi.SecurityPractice.mapper;

import com.saptarshi.SecurityPractice.dto.UserDto;
import com.saptarshi.SecurityPractice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
