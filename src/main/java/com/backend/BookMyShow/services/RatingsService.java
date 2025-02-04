package com.backend.BookMyShow.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.BookMyShow.exceptions.MovieNotFoundException;
import com.backend.BookMyShow.exceptions.UserNotFoundException;
import com.backend.BookMyShow.models.Movie;
import com.backend.BookMyShow.models.Rating;
import com.backend.BookMyShow.models.User;
import com.backend.BookMyShow.repositories.MovieRepository;
import com.backend.BookMyShow.repositories.RatingRepository;
import com.backend.BookMyShow.repositories.UserRepository;

@Service
public class RatingsService {
    UserRepository userRepository;
    MovieRepository movieRepository;
    RatingRepository ratingRepository;

    @Autowired
    public RatingsService(UserRepository userRepository,
                          MovieRepository movieRepository,
                          RatingRepository ratingRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }


    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException {
        Optional<User> uOptional = userRepository.findById(userId);
        if(uOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        Optional<Movie> mOptional = movieRepository.findById(movieId);
        if(mOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }

        User user = uOptional.get();
        Movie movie = mOptional.get();

        Rating ratingObj = new Rating();

        Optional<Rating> rOptional = ratingRepository.findByUserAndMovie(user, movie);
        if(rOptional.isPresent()){
            ratingObj = rOptional.get();
        }

        ratingObj.setMovie(movie);
        ratingObj.setUser(user);
        ratingObj.setRating(rating);

        ratingRepository.save(ratingObj);

        return ratingObj;
    }

    public double getAverageRating(int movieId) throws MovieNotFoundException {

        Optional<Movie> mOptional = movieRepository.findById(movieId);
        if(mOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }

        Movie movie = mOptional.get();
        Double total = 0.0;
        int count = 0;
        List<Rating> ratings = ratingRepository.findAllByMovieId(movieId);
        for(Rating r:ratings){
            count++;
            total += r.getRating();
        }
        if(count==0) return 0.0;

        return total/count;
    }

}
