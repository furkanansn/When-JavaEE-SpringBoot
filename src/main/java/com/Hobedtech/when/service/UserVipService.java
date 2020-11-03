package com.Hobedtech.when.service;


import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;


import java.util.List;

public interface UserVipService {

    UserVipDto getById(Long id);
    List<FriendDto> getByIdFollowers(Long id);
    Integer follwersCount(Long id);
   /* UserUserVip save(UserUserVip userUserVip);
    UserUserVip update(UserUserVip userUserVip);
    UserUserVip delete(UserUserVip userUserVip);*/
}
