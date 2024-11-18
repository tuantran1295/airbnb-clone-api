package com.tuantran.airbnb.repository.reserving;

import com.tuantran.airbnb.entity.reserving.Reserving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservingRepository extends JpaRepository<Reserving, Long> {
}
