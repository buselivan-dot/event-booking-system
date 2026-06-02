package com.system.eventBooking.controllers;

import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventEntity>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> getEventById(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    @PostMapping
    public ResponseEntity<EventEntity> createEvent(@RequestBody EventEntity event){
        EventEntity created = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable Long id){
        eventService.cancelEvent(id);
        return ResponseEntity.noContent().build();
    }


}
