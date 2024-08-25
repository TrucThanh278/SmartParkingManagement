package com.ou.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DTOUserResponse {
    String username;
    String firstName;
    String lastName;
    String email;
    String phone;
    String avatar;
    String roleName;
}
