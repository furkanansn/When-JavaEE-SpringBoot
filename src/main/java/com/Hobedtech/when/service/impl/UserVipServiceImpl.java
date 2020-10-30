package com.Hobedtech.when.service.impl;


import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.UserVipRepository;
import com.Hobedtech.when.service.UserVipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserVipServiceImpl implements UserVipService {
    private final UserVipRepository userVipRepository;
    private final ModelMapper modelMapper;

    public UserVipServiceImpl(UserVipRepository userVipRepository, ModelMapper modelMapper) {
        this.userVipRepository = userVipRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UsrVp> get() {
        return userVipRepository.findAll();
    }

    @Override
    public UserVipDto getById(Long id) {
        return modelMapper.map(userVipRepository.getOne(id),UserVipDto.class);
    }

    @Override
    public ProfileUserDtoUsers getByIdFollowers(Long id) {
        return modelMapper.map(userVipRepository.getOne(id),ProfileUserDtoUsers.class);
    }

    @Override
    public Integer follwersCount(Long id) {

        return userVipRepository.countFollowers(id);
    }

}
