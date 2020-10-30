package com.Hobedtech.when.service;

import com.Hobedtech.when.entity.UsrVp;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface SearchVenueService {
    List<UsrVp> sarchByVenueName(String venueName);
}
