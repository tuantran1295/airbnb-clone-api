package com.tuantran.airbnb.repository.homestay;

import com.tuantran.airbnb.dto.homestay.HomestayDTO;;
import com.tuantran.airbnb.entity.homestay.Homestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomestayRepository extends JpaRepository<Homestay, Long> {
    @Query(value = """
      select id, name, description, type, status, phone_number,
            address, guests, bedrooms, bathrooms, images, avg_price
            from homestay inner join (
            select h.id, avg(ha.price) as avg_price
            from homestay h
            join homestay_availability ha on h.id = ha.homestay_id
            where ha.status = 0
            and h.guests >= :guests
            and ha.date between cast(:checkinDate as date) and cast(:checkoutDate as date)
            group by h.id
            having count(ha.date) = (cast(:checkoutDate as date) - cast(:checkinDate as date)) + 1)
            as vh using (id)
            """, nativeQuery = true)
    List<HomestayDTO> searchHomestay(@Param("guests") Integer guests,
                                     @Param("checkinDate") String checkinDate,
                                     @Param("checkoutDate") String checkoutDate);
}
