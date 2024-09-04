package com.ou.mappers;

import com.ou.dto.request.ChangePasswordRequestDTO;
import com.ou.dto.request.UserRequestDTO;
import com.ou.dto.request.UserUpdateRequestDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roleId.name", target = "roleName")
    UserResponseDTO toUserResponse(User user);
    
    User toUser(UserRequestDTO dtoUserRequest);
    
    @Mapping(target = "email", ignore = true)
    void updateUserFromDto(@MappingTarget User user,UserUpdateRequestDTO dto);
    
    @Mapping(source = "roleId.name", target = "roleName")
    UserResponseDTO toDTO(User user);
    
}

