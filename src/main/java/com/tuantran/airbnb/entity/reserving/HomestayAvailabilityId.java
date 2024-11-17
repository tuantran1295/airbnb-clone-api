package com.tuantran.airbnb.entity.reserving;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode
public class HomestayAvailabilityId {
    private Long homestayId;
    private LocalDate date;
}
