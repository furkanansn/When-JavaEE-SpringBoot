package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.DiscoverDto;
import com.Hobedtech.when.service.impl.DiscoverServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.EventCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class DiscoverApi {
    private final DiscoverServiceImpl discoverServiceImpl;

    public DiscoverApi(DiscoverServiceImpl discoverServiceImpl) {
        this.discoverServiceImpl = discoverServiceImpl;
    }
    @GetMapping("/discover/initialize")
    public ResponseEntity<List<DiscoverDto>> getDiscoverInitialize(@RequestParam String cityName, @RequestParam String date){
        List<DiscoverDto> discoverDtos = discoverServiceImpl.getDtoByDateAndCity(cityName,date);
        return ResponseEntity.ok(discoverDtos);

    }

    @GetMapping("/discover/cyprus")
    public ResponseEntity<List<DiscoverDto>> getDiscoverForCyprus(){
        List<DiscoverDto> discoverDtos = discoverServiceImpl.getDiscoversForCyprusCity();
        return ResponseEntity.ok(discoverDtos);
    }
}
