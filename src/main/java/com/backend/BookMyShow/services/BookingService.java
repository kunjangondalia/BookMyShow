package com.backend.BookMyShow.services;

import com.backend.BookMyShow.models.*;
import com.backend.BookMyShow.repositories.BookingRepository;
import com.backend.BookMyShow.repositories.ShowRepository;
import com.backend.BookMyShow.repositories.ShowSeatRepository;
import com.backend.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(
            int showId,
            int userId,
            List<Integer> showSeatIds
    ){
        // 1. Get User from db
           Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isEmpty()){
                throw new RuntimeException("User not found");
            }
            User user = userOptional.get();

        // 2. Get show from db
            Optional<Show> showOptional = showRepository.findById(showId);
            if(showOptional.isEmpty()){
                throw new RuntimeException("Show not found");
            }
            Show show = showOptional.get();

        // 3. Get showSeats
        // 4. Check for availability of the seats
        // 6. otherwise throw exception
            List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);
            for(ShowSeat showSeat: showSeatList){
                if(showSeat.getShowSeatStatus() != ShowSeatStatus.EMPTY){
                    throw new RuntimeException("Show seats are not available at the moment.");
                }
            }

        // 5. if all are available, mark the status to blocked
            for(ShowSeat showSeat:showSeatList){
                showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                showSeat.setBlockedAt(new Date());
            }

        // 5.1 Make sure db is updated with the status
            showSeatRepository.saveAll(showSeatList);

        // 7. get the booking object ready
        // 8. Find total amount of the seats.
        // 8.1 Implement a different PriceCalculatorService
        int amount = 2000;
        // 9. Create, save and return the booking object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingDate(new Date());
        booking.setAmount(amount);
        booking.setPaymentList(new ArrayList<>());
        booking.setShowSeatList(showSeatList);
        bookingRepository.save(booking);

        return booking;
    }
}
