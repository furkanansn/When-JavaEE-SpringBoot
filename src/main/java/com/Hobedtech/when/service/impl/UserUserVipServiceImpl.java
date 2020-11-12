package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.entity.FriendsStatus;
import com.Hobedtech.when.entity.UserUserVip;
import com.Hobedtech.when.repository.UserUserVipRepository;
import com.Hobedtech.when.service.UserUserVipService;
import org.springframework.stereotype.Service;

/**
 * When Created by furkanansin on Nov, 2020
 */
@Service
public class UserUserVipServiceImpl implements UserUserVipService {
    private final UserUserVipRepository userUserVipRepository;

    public UserUserVipServiceImpl(UserUserVipRepository userUserVipRepository) {
        this.userUserVipRepository = userUserVipRepository;
    }

    @Override
    public UserUserVip follow(UserUserVip userUserVip) {
        userUserVip.setFriendsStatus(FriendsStatus.ACTIVE);
        userUserVipRepository.save(userUserVip);
        return userUserVip;
    }

    @Override
    public UserUserVip unFollow(UserUserVip userUserVip) {
        userUserVip.setFriendsStatus(FriendsStatus.DEACTIVE);
        userUserVipRepository.save(userUserVip);
        return userUserVip;
    }
}
