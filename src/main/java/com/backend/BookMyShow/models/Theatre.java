package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Theatre extends BaseModel{

    String name;
    String address;
    int rating;
    @OneToMany
    List<Screen> screens;
    @OneToMany
    List<Movie> movies;
}
