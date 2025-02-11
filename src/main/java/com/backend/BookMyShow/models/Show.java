package com.backend.BookMyShow.models;

import jakarta.persistence.*;
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
    private Date startTime;
    private Date endTime;


    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
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
