package com.backend.BookMyShow.repositories;

import com.backend.BookMyShow.models.SeatType;
import com.backend.BookMyShow.models.SeatTypeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow,Integer> {
}
