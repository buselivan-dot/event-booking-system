package com.system.eventBooking.services;

import com.system.eventBooking.entities.BookingEntity;
import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.entities.UserEntity;
import com.system.eventBooking.enums.BookingStatus;
import com.system.eventBooking.enums.EventStatus;
import com.system.eventBooking.repositories.BookingRepository;
import com.system.eventBooking.repositories.EventRepository;
import com.system.eventBooking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, EventRepository eventRepository,
                          UserRepository userRepository){
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BookingEntity bookTicket(Long userId, Long eventId){
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("There is no event with such id"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("There is no user with such id"));
        BookingEntity booking = new BookingEntity();

        if(bookingRepository.existsByUserIdAndEventId(userId, eventId)){
            throw new RuntimeException("User has already booked this event");
        }
        if(event.getAvailableSeats() < 1){
            throw new RuntimeException("There is no available seats");
        }
        if(event.getStatus().equals(EventStatus.PASSED)){
            throw new RuntimeException("The event was cancelled");
        }
        event.setAvailableSeats(event.getAvailableSeats() -1);
        eventRepository.save(event);

        booking.setUser(user);
        booking.setEvent(event);
        booking.setStatus(BookingStatus.ACTIVE);
        booking.setBookedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
    @Transactional
    public void cancelBooking(Long bookingId, Long requestingUserId){
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("There is no booking with such id"));
        EventEntity event = booking.getEvent();
        UserEntity user = userRepository.findById(requestingUserId).orElseThrow(() -> new RuntimeException("There is no user with such id"));
        
        if(!booking.getUser().equals(user)){
            throw new RuntimeException("The user havent booked this event");
        }
        if(event.getStatus().equals(EventStatus.CANCELLED) || event.getStatus().equals(EventStatus.PASSED)){
            throw new RuntimeException("Event is not available");
        }

        event.setAvailableSeats(event.getAvailableSeats()+1);
        eventRepository.save(event);

        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);
    }

}
