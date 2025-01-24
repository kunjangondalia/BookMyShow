package com.backend.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{
    private Date time;
    private Double amount;
    private String refNumber;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode mode;
    @Enumerated(EnumType.ORDINAL)
    private PaymentGateway paymentGateway;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @ManyToOne
    private Booking booking;
}
