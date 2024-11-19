package com.tuantran.airbnb.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.tuantran.airbnb.dto.reserving.response.ReservingResponse;
import com.tuantran.airbnb.entity.reserving.Reserving;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservingMapper {
    @Mapping(source = "id", target = "reserveId")
    ReservingResponse toResponse(Reserving reserving);

}
