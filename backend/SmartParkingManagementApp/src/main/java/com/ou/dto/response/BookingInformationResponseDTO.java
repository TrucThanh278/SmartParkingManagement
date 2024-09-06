package com.ou.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Vehicle;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingInformationResponseDTO {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Integer parkingSpotId;
    private Integer vehicleId;
}
