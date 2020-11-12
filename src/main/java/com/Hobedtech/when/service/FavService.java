package com.Hobedtech.when.service;

import com.Hobedtech.when.PostDto.FavPostDto;
import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.entity.EventsUsers;
import com.Hobedtech.when.entity.User;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface FavService {
    List<FavDto> getUsersByEventId(Long id);
    List<FavDto> getUsersByEventIdAndUserName(Long id, String username);
    EventsUsers save(FavPostDto favPostDto);
    EventsUsers delete(Long eventId, Long userId);

}
