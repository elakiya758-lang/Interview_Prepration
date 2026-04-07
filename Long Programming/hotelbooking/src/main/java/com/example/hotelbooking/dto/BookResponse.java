package com.example.hotelbooking.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long bookingId;

    private String userName;

    private String roomNumber;
    private String roomType;

    private Date checkIn;
    private Date checkOut;

    private double totalAmount;
}