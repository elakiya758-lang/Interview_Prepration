package com.example.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;
    private String roomType;
    private double price;
}