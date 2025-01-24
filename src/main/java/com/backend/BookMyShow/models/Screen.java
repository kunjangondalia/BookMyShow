package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Screen extends BaseModel{
    String name;
    @OneToMany
    List<Seat> seatList;
    List<ScreenFeature> screenFeatures;
}
