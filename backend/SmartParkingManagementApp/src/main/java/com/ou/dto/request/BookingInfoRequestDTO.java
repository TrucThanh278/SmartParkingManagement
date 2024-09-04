package com.ou.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingInfoRequestDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Boolean paymentStatus;
    private Integer parkingSpotId; 
    private Integer vehicleId; 
}
