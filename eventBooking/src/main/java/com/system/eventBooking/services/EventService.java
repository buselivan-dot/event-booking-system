package com.system.eventBooking.services;

import com.system.eventBooking.dto.CreateEventRequest;
import com.system.eventBooking.dto.EventDto;
import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.enums.EventStatus;
import com.system.eventBooking.mappers.EventMapper;
import com.system.eventBooking.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventDto createEvent(CreateEventRequest request){
        EventEntity event = eventMapper.toEntity(request);
        event.setAvailableSeats(request.getAmountOfTickets());
        event.setStatus(EventStatus.UPCOMING);
        return eventMapper.toDto(eventRepository.save(event));
    }

    public EventDto getEventById(Long id){
        return eventMapper.toDto(eventRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no event with such id!")));
    }
    public List<EventDto> getAllEvents(){
        return eventRepository.findAll().stream().map(eventMapper::toDto).collect(Collectors.toList());
    }
    public void cancelEvent(Long id){
        EventEntity event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("No event with such id"));
        event.setStatus(EventStatus.CANCELLED);
        eventRepository.save(event);
    }

}
