package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

@Entity
public class ShowSeat extends BaseModel{
    @OneToOne
    Show show;
    @OneToOne
    Seat seat;
    @Enumerated(EnumType.ORDINAL)
    ShowSeatStatus showSeatStatus;
}
