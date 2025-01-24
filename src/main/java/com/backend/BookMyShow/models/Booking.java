package com.backend.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel{
    private Date bookingDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<ShowSeat> showSeatList;
    @OneToMany
    private List<Payment> paymentList;
    private int amount;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus status;

}
