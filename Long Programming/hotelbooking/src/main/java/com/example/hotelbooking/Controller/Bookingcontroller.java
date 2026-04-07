package com.example.hotelbooking.Controller;

import com.example.hotelbooking.dto.BookResponse;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.Service.Bookingservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class Bookingcontroller {

    private final Bookingservice bookingService;

    public Bookingcontroller(Bookingservice bookingservice) {
        this.bookingService = bookingservice;
    }

    @PostMapping("/{userId}/{roomId}")
    public Booking createBooking(@PathVariable int userId,
                                 @PathVariable Long roomId,
                                 @RequestBody Booking booking) {
        return bookingService.createBooking(userId, roomId, booking);
    }

    @GetMapping("/GETBOOKING")
    public List<BookResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }
}