package com.example.hotelbooking.Repository;

import com.example.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}