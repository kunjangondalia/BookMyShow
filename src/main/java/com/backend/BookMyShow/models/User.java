package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User extends BaseModel{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookings;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
}
