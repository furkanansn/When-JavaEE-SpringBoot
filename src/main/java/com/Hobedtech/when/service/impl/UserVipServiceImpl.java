package com.Hobedtech.when.service.impl;


import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.repository.FriendsUserVipRepository;
import com.Hobedtech.when.repository.UserVipRepository;
import com.Hobedtech.when.service.UserVipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserVipServiceImpl implements UserVipService {
    private final UserVipRepository userVipRepository;
    private final FriendsUserVipRepository friendsUserVipRepository;
    private final ModelMapper modelMapper;

    public UserVipServiceImpl(UserVipRepository userVipRepository, FriendsUserVipRepository friendsUserVipRepository, ModelMapper modelMapper) {
        this.userVipRepository = userVipRepository;
        this.friendsUserVipRepository = friendsUserVipRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public UserVipDto getById(Long id) {
        return modelMapper.map(userVipRepository.getOne(id),UserVipDto.class);
    }

    @Override
    public List<FriendDto> getByIdFollowers(Long id) {
        return Arrays.asList(modelMapper.map(friendsUserVipRepository.getfollowersForProfile(id),FriendDto[].class));
    }

    @Override
    public Integer follwersCount(Long id) {

        return userVipRepository.countFollowers(id);
    }
}
