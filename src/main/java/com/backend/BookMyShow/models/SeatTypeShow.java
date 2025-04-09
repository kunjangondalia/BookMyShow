package com.backend.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SeatTypeShow extends BaseModel{
    @ManyToOne
    private Show show;

    @ManyToOne
    private SeatType seatType;
    private double price;
}
