package com.tuantran.airbnb.repository.homestay;

import com.tuantran.airbnb.entity.homestay.Homestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayRepository extends JpaRepository<Homestay, Long> {
}
