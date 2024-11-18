package com.tuantran.airbnb.constant.homestay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HomestayStatus {

    DRAFT(-1),
    INACTIVE(0),
    ACTIVE(1),
    CLOSED(2)
    ;
    private final int value;

}
