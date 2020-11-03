package com.Hobedtech.when.service;

import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.entity.EventsUsers;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface FavService {
    List<FavDto> getUsersByEventId(Long id);
    List<FavDto> getUsersByEventIdAndUserName(Long id, String username);
    EventsUsers save(EventsUsers userEvents);
    EventsUsers delete(EventsUsers eventsUsers);

}
