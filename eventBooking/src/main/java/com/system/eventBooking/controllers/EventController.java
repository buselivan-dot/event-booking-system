package com.system.eventBooking.controllers;

import com.system.eventBooking.dto.CreateEventRequest;
import com.system.eventBooking.dto.EventDto;
import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;


    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody @Valid CreateEventRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(request));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable Long id){
        eventService.cancelEvent(id);
        return ResponseEntity.noContent().build();
    }


}
