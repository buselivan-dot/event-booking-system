package com.system.eventBooking.dto;

import com.system.eventBooking.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String eventName;
    private String city;
    private LocalDateTime date;
    private Long price;
    private Long availableSeats;
    private EventStatus status;
}
