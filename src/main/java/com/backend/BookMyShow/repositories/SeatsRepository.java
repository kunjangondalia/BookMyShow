package com.backend.BookMyShow.repositories;

import com.backend.BookMyShow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsRepository extends JpaRepository<Seat,Integer> {
}
