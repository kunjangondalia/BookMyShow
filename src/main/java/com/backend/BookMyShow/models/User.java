package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany
    private List<Booking> bookings;
}
