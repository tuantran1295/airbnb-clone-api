package com.tuantran.airbnb.controller;

import com.tuantran.airbnb.dto.reserving.request.ReservingRequest;
import com.tuantran.airbnb.dto.reserving.response.ReservingResponse;
import com.tuantran.airbnb.service.reserving.ReservingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class ReservingController {
    private final ReservingService service;

    @PostMapping
    ReservingResponse reserveHomestay(@Valid @RequestBody ReservingRequest request) {
        log.info("Reserving request: {}", request);
        return service.reserve(request);
    }
}
