package com.example.hotelbooking.Repository;


import com.example.hotelbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByUseridUserId(Long userId);
}