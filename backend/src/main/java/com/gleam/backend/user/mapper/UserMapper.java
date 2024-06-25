package com.gleam.backend.user.mapper;

import com.gleam.backend.common.dto.UserDto;
import com.gleam.backend.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
