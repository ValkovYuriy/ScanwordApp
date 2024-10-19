package com.quad.ScanwordApp.mapper;


import com.quad.ScanwordApp.dto.UserDto;
import com.quad.ScanwordApp.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toUser(UserDto userDto);
}
