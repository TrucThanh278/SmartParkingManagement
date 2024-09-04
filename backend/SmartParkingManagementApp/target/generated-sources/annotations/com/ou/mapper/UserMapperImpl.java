package com.ou.mapper;

import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.request.DTOUserUpdateRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.pojo.Role;
import com.ou.pojo.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T18:01:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public DTOUserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        DTOUserResponse.DTOUserResponseBuilder dTOUserResponse = DTOUserResponse.builder();

        dTOUserResponse.roleName( userRoleIdName( user ) );
        dTOUserResponse.id( user.getId() );
        dTOUserResponse.username( user.getUsername() );
        dTOUserResponse.firstName( user.getFirstName() );
        dTOUserResponse.lastName( user.getLastName() );
        dTOUserResponse.email( user.getEmail() );
        dTOUserResponse.phone( user.getPhone() );
        dTOUserResponse.avatar( user.getAvatar() );
        dTOUserResponse.address( user.getAddress() );
        dTOUserResponse.enabled( user.isEnabled() );

        return dTOUserResponse.build();
    }

    @Override
    public User toUser(DTOUserRequest dtoUserRequest) {
        if ( dtoUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setLastName( dtoUserRequest.getLastName() );
        user.setFirstName( dtoUserRequest.getFirstName() );
        user.setUsername( dtoUserRequest.getUsername() );
        user.setEmail( dtoUserRequest.getEmail() );
        user.setPassword( dtoUserRequest.getPassword() );
        user.setAddress( dtoUserRequest.getAddress() );
        user.setPhone( dtoUserRequest.getPhone() );
        user.setAvatar( dtoUserRequest.getAvatar() );

        return user;
    }

    @Override
    public void updateUserFromDto(User user, DTOUserUpdateRequest dto) {
        if ( dto == null ) {
            return;
        }

        user.setLastName( dto.getLastName() );
        user.setFirstName( dto.getFirstName() );
        user.setAddress( dto.getAddress() );
        user.setPhone( dto.getPhone() );
    }

    @Override
    public DTOUserResponse toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        DTOUserResponse.DTOUserResponseBuilder dTOUserResponse = DTOUserResponse.builder();

        dTOUserResponse.roleName( userRoleIdName( user ) );
        dTOUserResponse.id( user.getId() );
        dTOUserResponse.username( user.getUsername() );
        dTOUserResponse.firstName( user.getFirstName() );
        dTOUserResponse.lastName( user.getLastName() );
        dTOUserResponse.email( user.getEmail() );
        dTOUserResponse.phone( user.getPhone() );
        dTOUserResponse.avatar( user.getAvatar() );
        dTOUserResponse.address( user.getAddress() );
        dTOUserResponse.enabled( user.isEnabled() );

        return dTOUserResponse.build();
    }

    private String userRoleIdName(User user) {
        if ( user == null ) {
            return null;
        }
        Role roleId = user.getRoleId();
        if ( roleId == null ) {
            return null;
        }
        String name = roleId.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
