package com.gleam.backend.user.mapper;

import com.gleam.backend.user.domain.User;
import com.gleam.backend.user.ui.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
