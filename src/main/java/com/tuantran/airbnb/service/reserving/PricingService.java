package com.tuantran.airbnb.service.reserving;

import com.tuantran.airbnb.constant.reserving.Currency;
import com.tuantran.airbnb.dto.reserving.ReservingPrice;
import com.tuantran.airbnb.entity.reserving.HomestayAvailability;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PricingService {
    private final DiscountService discountService;

    public ReservingPrice calculate(final List<HomestayAvailability> aDays) {
        final var nights = aDays.size();
        var subtotal = BigDecimal.ZERO;

        for(var aDay: aDays) {
            subtotal = subtotal.add(aDay.getPrice());
        }

        final var discount = discountService.getDiscountAmount(subtotal, nights);

        return ReservingPrice.builder()
                .subtotal(subtotal)
                .discount(discount)
                .totalAmount(subtotal.add(discount))
                .currency(Currency.USD.getValue())
                .build();
    }

}
