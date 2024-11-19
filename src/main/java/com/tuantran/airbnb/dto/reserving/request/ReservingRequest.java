package com.tuantran.airbnb.dto.reserving.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReservingRequest {
    @NotBlank(message = "request_id cannot be blank")
    private String requestId;

    @NotNull(message = "user_id cannot be null")
    private Long userId;

    @NotNull(message = "homestay_id cannot be blank")
    private Long homestayId;

    @NotNull(message = "checkin_date cannot be blank")
    private LocalDate checkinDate;

    @NotNull(message = "checkout_date cannot be blank")
    private LocalDate checkoutDate;

    @Positive(message = "guests must be positive")
    private Integer guests;

    @Length(max = 500, message = "note cannot be longer than 255 characters")
    private String note;

}
