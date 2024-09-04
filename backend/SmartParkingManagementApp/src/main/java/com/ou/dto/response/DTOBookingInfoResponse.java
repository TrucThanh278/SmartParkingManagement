package com.ou.dto.response;

import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DTOBookingInfoResponse {
    Integer id;
    Integer parkingSpotId;
    Integer vehicleId;
    Date startTime;
    Date endTime;
    Boolean paymentStatus;
}
