package com.system.eventBooking.mappers;

import com.system.eventBooking.dto.CreateEventRequest;
import com.system.eventBooking.dto.EventDto;
import com.system.eventBooking.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    EventEntity toEntity(CreateEventRequest request);

    EventDto toDto(EventEntity event);
}
