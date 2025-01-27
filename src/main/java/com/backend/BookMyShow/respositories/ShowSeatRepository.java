package com.backend.BookMyShow.respositories;

import com.backend.BookMyShow.models.ShowSeat;
import com.backend.BookMyShow.models.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {
    List<ShowSeat> findAllByIdsAndStatus(Iterable<Integer> integers, ShowSeatStatus showSeatStatus);

}
