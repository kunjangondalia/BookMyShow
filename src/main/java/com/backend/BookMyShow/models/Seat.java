package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;

@Entity
public class Seat {

    String name;
    int row;
    int col;
    SeatType seatType;
}
