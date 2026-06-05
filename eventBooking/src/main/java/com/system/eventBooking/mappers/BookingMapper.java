package com.system.eventBooking.mappers;


import com.system.eventBooking.dto.BookingDto;
import com.system.eventBooking.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "eventName", source = "event.eventName")
    @Mapping(target = "eventDate", source = "event.date")
    BookingDto toDto(BookingEntity booking);
}
