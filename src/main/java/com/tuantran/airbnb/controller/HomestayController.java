package com.tuantran.airbnb.controller;

import com.tuantran.airbnb.dto.common.response.ResponseDTO;
import com.tuantran.airbnb.dto.homestay.request.HomestaySearchRequest;
import com.tuantran.airbnb.entity.homestay.Homestay;
import com.tuantran.airbnb.service.ResponseFactory;
import com.tuantran.airbnb.service.homestay.HomestayService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/homestays")
@RequiredArgsConstructor
public class HomestayController {

    private final HomestayService service;
    private final ResponseFactory responseFactory;

    @GetMapping("/{id}")
    public Homestay getHomestayById(@PathVariable Long id) {
        return service.getHomestayById(id);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    public Page<Homestay> getHomestaysWithPagination(@PathVariable int offSet, @PathVariable int pageSize) {
        return service.getHomestaysWithPagination(offSet, pageSize);
    }

    @GetMapping
    public ResponseDTO searchHomestay(@RequestParam(value = "guests") Integer guests,
                                      @RequestParam(value = "checkin_date") String checkinDate,
                                      @RequestParam(value = "checkout_date") String checkoutDate){

        var request = HomestaySearchRequest.builder()
                .checkinDate(LocalDate.parse(checkinDate))
                .checkoutDate(LocalDate.parse(checkoutDate))
                .guests(guests)
                .build();

        var result = service.searchHomestays(request);

        return responseFactory.response(result);
    }
}
