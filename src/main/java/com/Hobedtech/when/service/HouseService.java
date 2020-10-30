package com.Hobedtech.when.service;


import com.Hobedtech.when.dto.HouseInitializeDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HouseService {
    TPage<Events> getAllPageable(Pageable pageable);

    List<HouseInitializeDto> getHouseInitialize(Long userId);

}
