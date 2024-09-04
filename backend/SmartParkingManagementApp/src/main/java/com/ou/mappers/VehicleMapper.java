package com.ou.mappers;

import com.ou.dto.request.VehicleRequestDTO;
import com.ou.pojo.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mappings({
        @Mapping(target = "userId.id", source = "userId"),
        @Mapping(target = "vehicleCategoryId.id", source = "vehicleCategoryId"),
    })
    Vehicle addVehicle(VehicleRequestDTO dtoVehicleRequest);
}
