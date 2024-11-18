package com.tuantran.airbnb.service.reserving;

import com.tuantran.airbnb.constant.common.ResponseCode;
import com.tuantran.airbnb.constant.reserving.AvailabilityStatus;
import com.tuantran.airbnb.entity.reserving.HomestayAvailability;
import com.tuantran.airbnb.exception.BusinessException;
import com.tuantran.airbnb.repository.reserving.HomestayAvailabilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AvailabilityService {
    private static final int NIGHT_MAX = 365;

    private final HomestayAvailabilityRepository availabilityRepository;

    public List<HomestayAvailability> checkAvailabilityForReserving(
            final Long homestayId,
            final LocalDate checkinDate,
            final LocalDate checkoutDate
    ) {
        final int nights = (int) ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        if (nights > NIGHT_MAX) {
            throw new BusinessException(ResponseCode.NIGHTS_INVALID);
        }

        final var aDays = availabilityRepository.findAndLockHomestayAvailability(
                homestayId,
                AvailabilityStatus.AVAILABLE.getValue(),
                checkinDate,
                checkoutDate
        );

        if (aDays.isEmpty() || aDays.size() < nights) {
            throw new BusinessException(ResponseCode.HOMESTAY_BUSY);
        }

        return aDays;
    }

    @Transactional
    public void saveAll(List<HomestayAvailability> aDays) {
        availabilityRepository.saveAll(aDays);
    }
}
