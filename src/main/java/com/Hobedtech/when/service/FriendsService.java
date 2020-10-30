package com.Hobedtech.when.service;

import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.entity.Friends;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface FriendsService {
    List<FriendDto> getFriendsById(Long userId);

    List<FriendDto> getFriendsByIdAndName(Long userId,String username);

    Boolean save(Friends friends);

    Friends update(Friends friends);

    Friends delete(Friends friends);

}
