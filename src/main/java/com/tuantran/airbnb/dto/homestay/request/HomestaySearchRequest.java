package com.tuantran.airbnb.dto.homestay.request;

import com.tuantran.airbnb.constant.reserving.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomestaySearchRequest {
    private Integer wardId;
    private Integer districtId;
    private Integer provinceId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer guests;
}
