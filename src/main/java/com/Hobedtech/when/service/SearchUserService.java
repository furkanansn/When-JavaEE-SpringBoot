package com.Hobedtech.when.service;

import com.Hobedtech.when.entity.User;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface SearchUserService {
    List<User> searchByUserName(String userName);
}
