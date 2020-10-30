package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.SearchVenueRepository;
import com.Hobedtech.when.service.SearchVenueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Service
public class SearchVenueServiceImpl implements SearchVenueService {
    private final SearchVenueRepository searchVenueRepository;

    public SearchVenueServiceImpl(SearchVenueRepository searchVenueRepository) {
        this.searchVenueRepository = searchVenueRepository;
    }

    @Override
    public List<UsrVp> sarchByVenueName(String venueName) {
        return searchVenueRepository.findUsrVpsByUsernameStartsWith(venueName);

    }
}
