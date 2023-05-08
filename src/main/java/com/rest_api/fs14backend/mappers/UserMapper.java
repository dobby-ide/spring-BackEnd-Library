package com.rest_api.fs14backend.mappers;

import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDTO dto);

    UserDTO userToUserDto(User user);
}
