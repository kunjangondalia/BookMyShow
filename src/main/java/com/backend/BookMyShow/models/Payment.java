package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Payment extends BaseModel{
    Date time;
    Double amount;
    PaymentGateway paymentGateway;
    PaymentStatus paymentStatus;
    Booking booking;
}
