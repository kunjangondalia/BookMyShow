package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "shows")
@Getter
@Setter
public class Show extends BaseModel{
    private Date time;
    private int duration;
    private Language language;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theatre theatre;
    @ManyToOne
    private Screen Screen;
    @OneToMany
    private List<ShowSeat> showSeatList;
    @OneToMany
    private List<ShowSeatType> showSeatTypes;
}
