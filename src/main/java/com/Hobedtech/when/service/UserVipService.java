package com.Hobedtech.when.service;


import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.UsrVp;

import java.util.List;

public interface UserVipService {
    List<UsrVp> get();
    UserVipDto getById(Long id);
    ProfileUserDtoUsers getByIdFollowers(Long id);
    Integer follwersCount(Long id);
}
