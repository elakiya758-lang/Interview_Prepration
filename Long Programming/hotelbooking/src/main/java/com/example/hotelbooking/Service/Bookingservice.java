package com.example.hotelbooking.Service;

import com.example.hotelbooking.dto.BookResponse;
import com.example.hotelbooking.entity.*;
import com.example.hotelbooking.Repository.*;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Bookingservice {

    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final RoomRepo roomRepo;

    public Bookingservice(BookingRepo bookingRepo,
                           UserRepo userRepo,
                           RoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
    }

    public Booking createBooking(int userId, Long roomId, Booking booking) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        booking.setUserid(user);
        booking.setRoomid(room);

        long days = (booking.getCheckOut().getTime() - booking.getCheckIn().getTime())
                / (1000 * 60 * 60 * 24);

        booking.setTotalAmount(days * room.getPrice());

        return bookingRepo.save(booking);
    }

    public List<BookResponse> getAllBookings() {
        return bookingRepo.findAll().stream().map(b -> new BookResponse(
                b.getBookingId(),
                b.getUserid().getName(),
                b.getRoomid().getRoomNumber(),
                b.getRoomid().getRoomType(),
                b.getCheckIn(),
                b.getCheckOut(),
                b.getTotalAmount()
        )).collect(Collectors.toList());
    }
}