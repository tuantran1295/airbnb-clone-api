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
    public ResponseDTO searchHomestayWithPagination(@RequestParam(value = "guests") Integer guests,
                                                    @RequestParam(value = "checkin_date") String checkinDate,
                                                    @RequestParam(value = "checkout_date") String checkoutDate,
                                                    @RequestParam(value = "ward_id", required = false) Integer wardId,
                                                    @RequestParam(value = "district_id", required = false) Integer districtId,
                                                    @RequestParam(value = "province_id", required = false) Integer provinceId,
                                                    @RequestParam(value = "page", required = true) Integer page,
                                                    @RequestParam(value = "page_size", required = true) Integer pageSize){

        var request = HomestaySearchRequest.builder()
                .checkinDate(LocalDate.parse(checkinDate))
                .checkoutDate(LocalDate.parse(checkoutDate))
                .guests(guests)
                .wardId(wardId)
                .districtId(districtId)
                .provinceId(provinceId)
                .build();

        var result = service.searchHomestaysWithPagination(request, page, pageSize);

        return responseFactory.response(result);
    }
}
