package com.tuantran.airbnb.constant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS("SUCCESS", 200, "success"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500, "internal server error"),
    BAD_REQUEST("BAD_REQUEST", 400, "bad request"),
    INVALID_PARAMS("INVALID_PARAMS", 400, "invalid params"),

    HOMESTAY_NOT_FOUND("HOMESTAY_NOT_FOUND", 404, "homestay not found"),
    HOMESTAY_NOT_ACTIVE("HOMESTAY_NOT_ACTIVE", 404, "homestay not active"),

    GUESTS_INVALID("GUESTS_INVALID", 400, "invalid guests number"),
    CHECKIN_DATE_INVALID("CHECKIN_DATE_INVALID", 400, "checkin date is not valid"),
    NIGHTS_INVALID("NIGHTS_INVALID", 400, "night number is invalid"),
    HOMESTAY_BUSY("HOMESTAY_BUSY", 400, "current homestay is booked"),
    ;

    private final String type;
    private final Integer code;
    private final String message;
}
