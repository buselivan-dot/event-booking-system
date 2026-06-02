package com.system.eventBooking.services;

import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.enums.EventStatus;
import com.system.eventBooking.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    public EventEntity createEvent(EventEntity event){
        return eventRepository.save(event);
    }

    public EventEntity getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no event with such id!"));
    }
    public List<EventEntity> getAllEvents(){
        return eventRepository.findAll();
    }
    public void cancelEvent(Long id){
        EventEntity event = getEventById(id);
        event.setStatus(EventStatus.CANCELLED);
        eventRepository.save(event);
    }

}
