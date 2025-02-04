package com.backend.BookMyShow.repositories;

import com.backend.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {
//    List<ShowSeat> findAllByIdsAndStatus(Iterable<Integer> integers, ShowSeatStatus showSeatStatus);

}
