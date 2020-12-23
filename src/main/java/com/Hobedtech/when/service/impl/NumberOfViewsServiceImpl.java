package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.repository.HouseRepository;
import com.Hobedtech.when.service.NumberOfViewsService;
import org.springframework.stereotype.Service;

/**
 * when Created by furkanansin on Dec, 2020
 */
@Service
public class NumberOfViewsServiceImpl implements NumberOfViewsService {
    final private HouseRepository houseRepository;

    public NumberOfViewsServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public void numberOfViews(Long eventId) {
        Events events = houseRepository.getOne(eventId);
        events.setNumberOfViews(events.getNumberOfViews() + 1);
    }
}
