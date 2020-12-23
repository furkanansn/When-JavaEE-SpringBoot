package com.Hobedtech.when.service;


import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.UsrVp;


import java.util.List;

public interface UserVipService {

    UserVipDto getById(Long id);
    List<FriendDto> getByIdFollowers(Long id);
    Integer follwersCount(Long id);

    Long register(UsrVp userVip);
    String confirm();

    Events addEvent(Events events);
    Boolean deleteEvents(Long id);

    List<Events> getEvents(Long userVipId);



}
