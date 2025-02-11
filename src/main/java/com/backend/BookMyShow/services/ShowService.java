package com.backend.BookMyShow.services;


import com.backend.BookMyShow.exceptions.*;
import com.backend.BookMyShow.models.*;
import com.backend.BookMyShow.repositories.*;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {
    private MovieRepository movieRepository;
    private ScreenRepository screenRepository;
    private SeatsRepository seatsRepository;
    private SeatTypeShowRepository seatTypeShowRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public ShowService(MovieRepository movieRepository, ScreenRepository screenRepository,
                           SeatsRepository seatsRepository, SeatTypeShowRepository seatTypeShowRepository,
                           ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                           UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.seatsRepository = seatsRepository;
        this.seatTypeShowRepository = seatTypeShowRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime,
                           List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException {

        Optional<User> userOp = userRepository.findById(userId);
        if (!userOp.isPresent()) {
            throw new UserNotFoundException("User is not found");
        }
        User user = userOp.get();
        if (user.getUserType() != UserType.ADMIN) {
            throw new UnAuthorizedAccessException("Only admin have access");
        }

        Optional<Movie> movieOp = movieRepository.findById(movieId);
        if (!movieOp.isPresent()) {
            throw new MovieNotFoundException("Movie is not found");
        }
        Movie movie = movieOp.get();

        Optional<Screen> screenOp = screenRepository.findById(screenId);
        if (!screenOp.isPresent()) {
            throw new ScreenNotFoundException("Screen is not found");
        }
        Screen screen = screenOp.get();
        Hibernate.initialize(screen.getScreenFeatures());

        List<Feature> screenFeatures = screen.getScreenFeatures();
        if (screenFeatures == null || screenFeatures.isEmpty()) {
            throw new FeatureNotSupportedByScreen("Screen does not support any features.");
        }
        for (Feature feature : features) {
            if (!screenFeatures.contains(feature)) {
                throw new FeatureNotSupportedByScreen("Screen does not support the feature: " + feature);
            }
        }

        if (startTime.after(endTime) || startTime.before(new Date(System.currentTimeMillis()))) {
            throw new InvalidDateException("Start time should be before end time");
        }


        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setFeatures(features);
        show = showRepository.save(show);

        for (Pair<SeatType, Double> pair : pricingConfig) {
            SeatTypeShow seatTypeShow = new SeatTypeShow();
            seatTypeShow.setShow(show);
            seatTypeShow.setSeatType(pair.getFirst());
            seatTypeShow.setPrice(pair.getSecond());
            seatTypeShowRepository.save(seatTypeShow);
        }

        List<Seat> seats = seatsRepository.findAll().stream()
                .filter(seat -> seat.getScreen().getId() == screenId)
                .collect(Collectors.toList());

        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setShowSeatStatus(ShowSeatStatus.EMPTY);
            showSeatRepository.save(showSeat);
        }

        return show;
    }
}
