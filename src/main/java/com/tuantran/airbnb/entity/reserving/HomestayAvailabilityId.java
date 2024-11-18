package com.tuantran.airbnb.entity.reserving;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode
public class HomestayAvailabilityId implements Serializable {
    private Long homestayId;
    private LocalDate date;
}
