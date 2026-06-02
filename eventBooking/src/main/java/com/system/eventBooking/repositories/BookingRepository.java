package com.system.eventBooking.repositories;

import com.system.eventBooking.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    List<BookingEntity> findByUserId(Long id);
}
