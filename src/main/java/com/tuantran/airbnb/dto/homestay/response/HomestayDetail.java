package com.tuantran.airbnb.dto.homestay.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HomestayDetail {
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Integer type;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "images")
    private List<String> images;

    @Column(name = "avg_price")
    private Double avgPrice;
}
