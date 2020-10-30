package com.Hobedtech.when.service;

import com.Hobedtech.when.dto.DiscoverDto;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface DiscoverService {
    List<DiscoverDto> getDtoByDateAndCity(String cityName,String date);
}
