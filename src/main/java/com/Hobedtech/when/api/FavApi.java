package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.entity.EventsUsers;
import com.Hobedtech.when.service.impl.FavServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.FavCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class FavApi {
    private final FavServiceImpl favServiceImpl;

    public FavApi(FavServiceImpl favServiceImpl) {
        this.favServiceImpl = favServiceImpl;
    }
    @GetMapping("/users")
    public ResponseEntity<GeneralResponse> getUsersByEventId(@RequestParam Long eventId){
        return new GeneralApi().sendResponse(new GeneralResponse(true,favServiceImpl.getUsersByEventId(eventId),null));
    }

    @GetMapping("/users/search")
    public ResponseEntity<GeneralResponse> getUsersByEventIdAndUseName(@RequestParam Long eventId, @RequestParam String username){
        return new GeneralApi().sendResponse(new GeneralResponse(true,favServiceImpl.getUsersByEventIdAndUserName(eventId,username),null));


    }
    @PostMapping
    public ResponseEntity<GeneralResponse> save(@RequestBody EventsUsers userEvents){
        return new GeneralApi().sendResponse(new GeneralResponse(true,favServiceImpl.save(userEvents),null));
    }
}
