package com.Hobedtech.when.service.impl;


import com.Hobedtech.when.dto.HouseInitializeDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.repository.HouseRepository;
import com.Hobedtech.when.service.HouseService;
import com.Hobedtech.when.util.DateCurrent;
import com.Hobedtech.when.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
   private final HouseRepository eventRepository;

   private final ModelMapper modelMapper;
    public HouseServiceImpl(HouseRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TPage<Events> getAllPageable(Pageable pageable) {
        Page<Events> data = eventRepository.findAll(pageable);
        return new TPage<Events>();
    }

    @Override
    public List<HouseInitializeDto> getHouseInitialize(Long userId) {
        DateCurrent dateCurrent = new DateCurrent();
        List<Events> events = eventRepository.findAll(dateCurrent.getDate(),userId);
        return Arrays.asList(modelMapper.map(events,HouseInitializeDto[].class));
    }





}
