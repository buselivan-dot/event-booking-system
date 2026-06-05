package com.system.eventBooking.entities;

import com.system.eventBooking.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String description;

    //amount that could fit into stadium the number doesnt change
    private long amountOfTickets;

    //available seats at this second
    private long availableSeats;

    private String city;
    private LocalDateTime date;
    private long price;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    //joining with BookingEntity table
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;
}
