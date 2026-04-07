package com.example.hotelbooking.Service;



import com.example.hotelbooking.Repository.RoomRepo;
import com.example.hotelbooking.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Roomservice {

    private final RoomRepo roomRepo;

    public Roomservice(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }
}