package com.ou.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DTOVehicleRequest {
    private String plateNumber;
    private Integer userId;
    private Integer vehicleCategoryId;
}
