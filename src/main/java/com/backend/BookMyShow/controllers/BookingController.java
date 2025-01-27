package com.backend.BookMyShow.controllers;

import com.backend.BookMyShow.dtos.BookTicketRequestDTO;
import com.backend.BookMyShow.dtos.BookTicketResponseDTO;
import com.backend.BookMyShow.dtos.ResponseStatus;
import com.backend.BookMyShow.models.Booking;
import com.backend.BookMyShow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO request){

        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();

        try{
            Booking booking = bookingService.bookTicket(
                    request.getShowId(),
                    request.getUserId(),
                    request.getShowSeatIds()
            );
            responseDTO.setBookingId(booking.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setAmount(booking.getAmount());

        }catch(Exception ex){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }
}
