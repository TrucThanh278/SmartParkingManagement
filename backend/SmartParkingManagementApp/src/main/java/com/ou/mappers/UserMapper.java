package com.ou.mappers;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.request.DTOUserUpdateRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roleId.name", target = "roleName")
    DTOUserResponse toUserResponse(User user);
    
    User toUser(DTOUserRequest dtoUserRequest);
    
    @Mapping(target = "email", ignore = true)
    void updateUserFromDto(@MappingTarget User user,DTOUserUpdateRequest dto);
    
    @Mapping(source = "roleId.name", target = "roleName")
    DTOUserResponse toDTO(User user);
    
}

