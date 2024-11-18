package com.tuantran.airbnb.controller;

import com.tuantran.airbnb.entity.homestay.Homestay;
import com.tuantran.airbnb.service.homestay.HomestayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/homestays")
@RequiredArgsConstructor
public class HomestayController {

    private final HomestayService service;

    @GetMapping("/{id}")
    public Homestay getHomestayById(@PathVariable Long id) {
        return service.getHomestayById(id);
    }
}
