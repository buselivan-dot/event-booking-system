package com.system.eventBooking.dto;

import com.system.eventBooking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private String eventName;
    private LocalDateTime eventDate;
    private LocalDateTime bookedAt;
    private BookingStatus status;
}
