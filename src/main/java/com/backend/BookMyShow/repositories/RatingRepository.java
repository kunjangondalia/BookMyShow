package com.backend.BookMyShow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.BookMyShow.models.Movie;
import com.backend.BookMyShow.models.Rating;
import com.backend.BookMyShow.models.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer>{
    public List<Rating> findAllByMovieId(int movieId);
    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}
