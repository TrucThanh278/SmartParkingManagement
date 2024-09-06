package com.ou.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String avatar;
}

