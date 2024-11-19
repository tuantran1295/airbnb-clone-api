package com.tuantran.airbnb.dto.reserving;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservingPrice {
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String currency;
}
