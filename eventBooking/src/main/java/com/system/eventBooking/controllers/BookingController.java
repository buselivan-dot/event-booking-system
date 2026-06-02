package com.system.eventBooking.controllers;


import com.system.eventBooking.entities.BookingEntity;
import com.system.eventBooking.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingEntity>> getUserBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }
    @PostMapping
    public ResponseEntity<BookingEntity> bookTicket(@RequestParam Long userId, @RequestParam Long eventId){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.bookTicket(userId, eventId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id, @RequestParam Long requestingUserId){
        bookingService.cancelBooking(id, requestingUserId);
        return ResponseEntity.noContent().build();
    }

}
