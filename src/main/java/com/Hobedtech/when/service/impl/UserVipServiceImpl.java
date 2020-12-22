package com.Hobedtech.when.service.impl;


import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.EventRepository;
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
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public UserVipServiceImpl(UserVipRepository userVipRepository, FriendsUserVipRepository friendsUserVipRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.userVipRepository = userVipRepository;
        this.friendsUserVipRepository = friendsUserVipRepository;
        this.eventRepository = eventRepository;
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

    @Override
    public Long register(UsrVp userVip) {
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(  userVip.getPassword());
        userVip.setPassword(sha256hex);
        UsrVp usrVp = userVipRepository.save(userVip);

        return usrVp.getId();
    }

    @Override
    public String confirm() {
        return null;
    }

    @Override
    public Events addEvent(Events events) {
     return eventRepository.save(events);
    }

    @Override
    public Boolean deleteEvents(Long id) {
        Events event = eventRepository.getOne(id);
        try{
            eventRepository.delete(event);
            return true;
        }catch(Exception exception){
            return false;
        }

    }

    @Override
    public Events getEvents(Long userVipId) {

        return eventRepository.findAllByUserVips(userVipId);
    }

    @Override
    public Long login(String email, String password) {
        UsrVp usrVp = userVipRepository.getByEmail(email);
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        if(usrVp.getPassword().equals(sha256hex)){
            return usrVp.getId();
        }
        else{
            return 0L;
        }
    }
}
