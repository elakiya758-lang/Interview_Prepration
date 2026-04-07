package com.example.hotelbooking.Controller;

import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.Service.Roomservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class Roomcontroller {

    private final Roomservice roomService;

    public Roomcontroller(Roomservice roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/POSTROOM")
    public Room addRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @GetMapping("/GETROOM")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}