package com.tuantran.airbnb.service.homestay;

import com.tuantran.airbnb.dto.homestay.HomestayDTO;
import com.tuantran.airbnb.dto.homestay.request.HomestaySearchRequest;
import com.tuantran.airbnb.dto.homestay.response.HomestayDetail;
import com.tuantran.airbnb.entity.homestay.Homestay;
import com.tuantran.airbnb.repository.homestay.HomestayRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomestayService {
    private final HomestayRepository repository;

    public Homestay getHomestayById(Long id) {
        var homestay = repository.findById(id).orElse(null);
        return homestay;
    }

    public Page<Homestay> getHomestaysWithPagination(int offSet, int pageSize) {
        return repository.findAll(PageRequest.of(offSet, pageSize));
    }

    public List<HomestayDTO> searchHomestaysWithPagination(HomestaySearchRequest request, int pageNumber, int pageSize) {
        var checkinDate = request.getCheckinDate();
        var checkoutDate = request.getCheckoutDate();
        var offset = (pageNumber * pageSize) - pageSize;

        var homestays = repository.searchHomestayWithPagination(
                request.getGuests(),
                checkinDate.toString(),
                checkoutDate.toString(),
                request.getWardId(),
                request.getDistrictId(),
                request.getProvinceId(),
                pageSize,
                offset
        );
        return homestays;
    }
}
