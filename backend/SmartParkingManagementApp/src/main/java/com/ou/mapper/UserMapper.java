package com.ou.mapper;

import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roleId.name", target = "roleName")
    DTOUserResponse toUserResponse(User user);

    User toUser(DTOUserRequest dtoUserRequest);
}
