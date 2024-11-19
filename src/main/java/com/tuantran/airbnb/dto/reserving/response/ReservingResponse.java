package com.tuantran.airbnb.dto.reserving.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReservingResponse {
    private Long reserveId;
    private Long userId;
    private Long homestayId;
    private String checkinDate;
    private String checkoutDate;
    private Integer guests;

    private Integer status;

    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String currency;

    private String requestId;
    private String note;
}
