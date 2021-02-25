package com.Hobedtech.when.service;

import com.Hobedtech.when.PostDto.CheckInPostDto;
import com.Hobedtech.when.dto.CheckInDto;
import com.Hobedtech.when.dto.OtherUserDto;
import com.Hobedtech.when.entity.User;

import java.util.List;

public interface CheckInService {
    List<User> doCheckIn(CheckInPostDto checkInPostDto);
}
