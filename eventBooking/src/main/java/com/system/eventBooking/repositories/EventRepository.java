package com.system.eventBooking.repositories;

import com.system.eventBooking.entities.EventEntity;
import com.system.eventBooking.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByEventName(String eventName);
    List<EventEntity> findByDate(LocalDateTime date);
    List<EventEntity> findByPriceBetween(long min, long max);
    List<EventEntity> findByCity(String city);
    List<EventEntity> findByStatus(EventStatus status);
}
