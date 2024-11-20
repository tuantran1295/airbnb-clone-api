package com.tuantran.airbnb.repository.homestay;

import com.tuantran.airbnb.dto.homestay.HomestayDTO;;
import com.tuantran.airbnb.entity.homestay.Homestay;
import jakarta.persistence.criteria.CriteriaBuilder;
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
            and (:wardId is null or h.ward_id = :wardId)
            and (:districtId is null or h.district_id = :districtId)
            and (:provinceId is null or h.province_id = :provinceId)
            group by h.id
            having count(ha.date) = (cast(:checkoutDate as date) - cast(:checkinDate as date)) + 1)
            as vh using (id)
            limit :limit
            offset :offset
            """, nativeQuery = true)
    List<HomestayDTO> searchHomestayWithPagination(@Param("guests") Integer guests,
                                                   @Param("checkinDate") String checkinDate,
                                                   @Param("checkoutDate") String checkoutDate,
                                                   @Param("wardId") Integer wardId,
                                                   @Param("districtId") Integer districtId,
                                                   @Param("provinceId") Integer provinceId,
                                                   @Param("limit") Integer limit,
                                                   @Param("offset") Integer offset);
}
