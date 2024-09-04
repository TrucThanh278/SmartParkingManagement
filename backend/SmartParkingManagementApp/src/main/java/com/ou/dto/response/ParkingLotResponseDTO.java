package com.ou.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotResponseDTO {
    Integer id;
    String name;
    String address;
    Float pricePerHour;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
