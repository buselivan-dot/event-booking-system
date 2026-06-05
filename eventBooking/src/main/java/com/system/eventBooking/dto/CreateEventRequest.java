package com.system.eventBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class CreateEventRequest {
    @NotBlank(message = "Event name is required")
    private String eventName;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "City is required")
    private String city;
    @NotNull(message = "Date is required")
    private LocalDateTime date;
    @NotNull(message = "Price is required")
    private Long price;
    @NotNull(message = "Amount of tickets is required")
    private Long amountOfTickets;
}
