package com.tuantran.airbnb.constant.reserving;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReservingStatus {
    DRAFT(0),
    BOOKED(1),
    COMPLETED(2),
    CANCELLED(3)
    ;
    private final int value;
}
