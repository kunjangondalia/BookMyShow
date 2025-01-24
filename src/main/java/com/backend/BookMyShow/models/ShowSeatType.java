package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;

@Entity
public class ShowSeatType extends BaseModel{
    Show show;
    SeatType seatType;
    int price;
}
