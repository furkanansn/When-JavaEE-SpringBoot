package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.SearchUserRepository;
import com.Hobedtech.when.service.SearchUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Service
public class SearchUserServiceImpl implements SearchUserService {
    private final SearchUserRepository searchRepository;

    public SearchUserServiceImpl(SearchUserRepository searchRepository) {
        this.searchRepository = searchRepository;
    }


    @Override
    public List<User> searchByUserName(String userName) {
        List<User> users = searchRepository.findUsersByUsernameStartsWith(userName);
        return users;
    }
}

