package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Movie extends BaseModel{
    String name;
    String genre;
    int rating;
    List<String> languages;
    List<String> features;
    List<String> actors;
}
