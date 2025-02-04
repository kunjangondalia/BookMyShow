package com.backend.BookMyShow.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.BookMyShow.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{
}

