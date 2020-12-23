package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.dto.DiscoverDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.repository.DiscoverRepository;
import com.Hobedtech.when.service.DiscoverService;
import com.Hobedtech.when.service.NumberOfViewsService;
import jdk.jfr.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Service
public class DiscoverServiceImpl implements DiscoverService {
    private final DiscoverRepository discoverRepository;
    private final ModelMapper modelMapper;

    public DiscoverServiceImpl(DiscoverRepository discoverRepository, ModelMapper modelMapper) {
        this.discoverRepository = discoverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DiscoverDto> getDtoByDateAndCity(String cityName, String date) {
        List<Events> events = discoverRepository.findAll(cityName,date);
        List<DiscoverDto> discoverDto = Arrays.asList(modelMapper.map(events,DiscoverDto[].class));
        return discoverDto;
    }


}
