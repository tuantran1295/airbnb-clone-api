package com.tuantran.airbnb.service.reserving;

import com.tuantran.airbnb.constant.common.ResponseCode;
import com.tuantran.airbnb.constant.homestay.HomestayStatus;
import com.tuantran.airbnb.constant.reserving.AvailabilityStatus;
import com.tuantran.airbnb.constant.reserving.ReservingStatus;
import com.tuantran.airbnb.dto.reserving.request.ReservingRequest;
import com.tuantran.airbnb.dto.reserving.response.ReservingResponse;
import com.tuantran.airbnb.entity.reserving.Reserving;
import com.tuantran.airbnb.exception.BusinessException;
import com.tuantran.airbnb.mapper.ReservingMapper;
import com.tuantran.airbnb.repository.reserving.ReservingRepository;
import com.tuantran.airbnb.service.homestay.HomestayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservingService {
    private final ReservingRepository reservingRepository;
    private final AvailabilityService availabilityService;
    private final HomestayService homestayService;
    private final PricingService pricingService;
    private final ReservingMapper mapper;

    @SneakyThrows
    @Transactional
    public ReservingResponse reserve(final ReservingRequest request) {
        validateRequest(request);
        validateHomestay(request);

        final Long homestayId = request.getHomestayId();
        final LocalDate checkinDate = request.getCheckinDate();
        final LocalDate checkoutDate = request.getCheckoutDate();

        log.debug("[request_id={}] User user_id ={} is acquiring lock homestay_id={} from checkin_date={} to checkout_date={}",
                request.getRequestId(),
                request.getUserId(),
                homestayId,
                checkinDate,
                checkoutDate);
        final var aDays = availabilityService.checkAvailabilityForReserving(homestayId, checkinDate, checkoutDate);
        log.debug("[request_id={}], User user_id={} locked homestay_id={} from checkin_date={} to checkout_date={}",
                request.getRequestId(),
                request.getUserId(),
                request.getHomestayId(),
                checkinDate,
                checkoutDate);

        final var price = pricingService.calculate(aDays);
        final var reserving = Reserving.builder()
                .homestayId(homestayId)
                .userId(request.getUserId())
                .checkinDate(checkinDate)
                .checkoutDate(checkoutDate)
                .guests(request.getGuests())
                .subtotal(price.getSubtotal())
                .discount(price.getDiscount())
                .totalAmount(price.getTotalAmount())
                .currency(price.getCurrency())
                .note(request.getNote())
                .status(ReservingStatus.BOOKED.getValue())
                .requestId(request.getRequestId())
                .build();

        aDays.forEach(a -> a.setStatus(AvailabilityStatus.BOOKED.getValue()));

        availabilityService.saveAll(aDays);
        reservingRepository.save(reserving);
        log.info("[request_id={} User user_id={} ceated booking_id={} successfully",
                request.getRequestId(),
                request.getUserId(),
                reserving.getId());
        return mapper.toResponse(reserving);
    }

    private void validateRequest(final ReservingRequest request) {
        final var checkinDate = request.getCheckinDate();
        final var checkoutDate = request.getCheckoutDate();
        final var currentDate = LocalDate.now();

        if (checkinDate.isBefore(currentDate) || checkinDate.isAfter(checkoutDate)) {
            throw new BusinessException(ResponseCode.CHECKIN_DATE_INVALID);
        }

        if (request.getGuests() <= 0) {
            throw new BusinessException(ResponseCode.GUESTS_INVALID);
        }
    }

    private void validateHomestay(final ReservingRequest request) {
        final var homestay = homestayService.getHomestayById(request.getHomestayId());
        if (homestay == null) {
            throw new BusinessException(ResponseCode.HOMESTAY_NOT_FOUND);
        }

        if (homestay.getStatus() != HomestayStatus.ACTIVE.getValue()) {
            throw new BusinessException(ResponseCode.HOMESTAY_NOT_ACTIVE);
        }

        if (homestay.getGuests() < request.getGuests()) {
            throw new BusinessException(ResponseCode.GUESTS_INVALID);
        }
    }
}
