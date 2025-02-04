package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Rating extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Movie movie;
    private int rating;
}
