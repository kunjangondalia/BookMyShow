package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;

@Entity
public class Show extends BaseModel{
    Date time;
    @OneToOne
    Movie movie;
    @OneToOne
    Theatre theatre;
    @OneToOne
    Screen Screen;
    List<ShowSeat> showSeatList;
    List<ShowSeatType> showSeatTypes;
}
