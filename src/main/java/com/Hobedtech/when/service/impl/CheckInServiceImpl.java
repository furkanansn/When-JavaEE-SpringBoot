package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.PostDto.CheckInPostDto;
import com.Hobedtech.when.dto.CheckInDto;
import com.Hobedtech.when.dto.OtherUserDto;
import com.Hobedtech.when.entity.CheckIn;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.CheckInRepository;
import com.Hobedtech.when.service.CheckInService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final ModelMapper modelMapper;

    public CheckInServiceImpl(CheckInRepository checkInRepository, ModelMapper modelMapper) {
        this.checkInRepository = checkInRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> doCheckIn(CheckInPostDto checkInPostDto) {
        User user = new User();
        user.setId(checkInPostDto.getUser_id());

        Events events = new Events();
        events.setId(checkInPostDto.getEvent_id());

        CheckIn checkIn = new CheckIn();
        checkIn.setEvents(events);
        checkIn.setUsers(user);

        checkInRepository.save(checkIn);
        List<CheckIn> checkInsInEvent = checkInRepository.getCheckInByEvents_IdAndUsers_IdNot(checkInPostDto.getEvent_id(),checkInPostDto.getUser_id());


        List<User> userList = new ArrayList<>();
        for(int i = 0 ; i<checkInsInEvent.size() ; i++){
            User user1 = new User();
            user1 = checkInsInEvent.get(i).getUsers();
            userList.add(user1);
        }


        return userList;
    }
}
