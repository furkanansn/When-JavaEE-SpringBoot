package com.Hobedtech.when.service;

import com.Hobedtech.when.entity.UserUserVip;

/**
 * When Created by furkanansin on Nov, 2020
 */
public interface UserUserVipService {
    UserUserVip follow(UserUserVip userUserVip);

    UserUserVip unFollow(UserUserVip userUserVip);

}
