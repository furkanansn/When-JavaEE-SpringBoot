package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.PostDto.FavPostDto;
import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.entity.*;
import com.Hobedtech.when.repository.FavRepository;
import com.Hobedtech.when.repository.UserEventsRepository;
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
    private final UserEventsRepository userEventsRepository;
    private final ModelMapper modelMapper;

    public FavServiceImpl(FavRepository favRepository, UserEventsRepository userEventsRepository, ModelMapper modelMapper) {
        this.favRepository = favRepository;
        this.userEventsRepository = userEventsRepository;
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

    @Override
    public EventsUsers save(FavPostDto favPostDto) {
        EventsUsers eventsUsers = new EventsUsers();

        EventsUsers.EventsUserId eventsUserId = new EventsUsers.EventsUserId();
        eventsUserId.userId = favPostDto.getUserId();
        eventsUserId.eventId = favPostDto.getEventId();

        eventsUsers.setEventsUserId(eventsUserId);
        eventsUsers.setFavStatus("ACTIVE");
        return userEventsRepository.save(eventsUsers);
    }

    @Override
    public EventsUsers delete(Long eventId, Long userId) {
        EventsUsers eventsUsers = userEventsRepository.findByEventsUserId_EventIdAndEventsUserId_UserId( eventId,  userId);
        eventsUsers.setFavStatus("DEACTIVE");
        return userEventsRepository.save(eventsUsers);
    }

}
