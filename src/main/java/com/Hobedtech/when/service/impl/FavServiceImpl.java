package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.FavRepository;
import com.Hobedtech.when.service.FavService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Service
public class FavServiceImpl implements FavService {
    private final FavRepository favRepository;
    private final ModelMapper modelMapper;

    public FavServiceImpl(FavRepository favRepository, ModelMapper modelMapper) {
        this.favRepository = favRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FavDto> getUsersByEventId(Long id) {
            List<User> users = favRepository.findAll(id);
            return Arrays.asList(modelMapper.map(users,FavDto[].class));

    }

    @Override
    public List<FavDto> getUsersByEventIdAndUserName(Long id, String username) {
        List<User> users = favRepository.findAllSearch(id,username);
        return Arrays.asList(modelMapper.map(users,FavDto[].class));
    }
}
