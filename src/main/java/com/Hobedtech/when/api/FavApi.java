package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FavDto;
import com.Hobedtech.when.service.impl.FavServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import io.swagger.annotations.Api;
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
    public ResponseEntity<List<FavDto>> getUsersByEventId(@RequestParam Long eventId){
        return ResponseEntity.ok(favServiceImpl.getUsersByEventId(eventId));
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<FavDto>> getUsersByEventIdAndUseName(@RequestParam Long eventId, @RequestParam String username){
        return ResponseEntity.ok(favServiceImpl.getUsersByEventIdAndUserName(eventId,username));
    }
}
