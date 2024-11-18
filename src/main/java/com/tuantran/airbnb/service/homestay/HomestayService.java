package com.tuantran.airbnb.service.homestay;

import com.tuantran.airbnb.entity.homestay.Homestay;
import com.tuantran.airbnb.repository.homestay.HomestayRepository;
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

}
