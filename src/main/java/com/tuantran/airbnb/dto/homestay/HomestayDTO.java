package com.tuantran.airbnb.dto.homestay;

import java.util.List;

public interface HomestayDTO {

    Long getId();

    String getName();

    String getDescription();

    List<String> getImages();

    Integer getType();

    Integer getStatus();

    String getPhoneNumber();

    Integer getGuests();

    Integer getBedrooms();

    String getAddress();
}
