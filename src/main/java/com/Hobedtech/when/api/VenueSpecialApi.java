package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.service.impl.UserVipServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import com.sun.mail.iap.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * when Created by furkanansin on Dec, 2020
 */
@RestController
@RequestMapping("<API_KEY=>"+ ApiPaths.VENUEAPIKEY+ApiPaths.VenueCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class VenueSpecialApi {
    private final UserVipServiceImpl userVipService;


    public VenueSpecialApi(UserVipServiceImpl userVipService) {
        this.userVipService = userVipService;
    }

    @PostMapping
    public ResponseEntity<Long> registerVenue(UsrVp usrVp){
        return ResponseEntity.ok(userVipService.register(usrVp));
    }
    @PostMapping("event")
    public ResponseEntity<Events> addEvent(Events events){
        return ResponseEntity.ok(userVipService.addEvent(events));
    }

    @DeleteMapping("event")
    public ResponseEntity<Boolean> deleteEvent(Long id){
        return ResponseEntity.ok(userVipService.deleteEvents(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserVipDto> getUserVipProfile(@RequestParam Long id){
        UserVipDto userVipDto = userVipService.getById(id);
        Integer count = userVipService.follwersCount(id);
        userVipDto.setFollowersCount(count);
        return ResponseEntity.ok(userVipDto);
    }
    @GetMapping("/profile/followers")
    public ResponseEntity<List<FriendDto>> getUserVipProfileFollowers(@RequestParam Long id){
        return ResponseEntity.ok(userVipService.getByIdFollowers(id));
    }

    @GetMapping("event")
    public ResponseEntity<Events> getEventsByVenueId(Long id){
        return ResponseEntity.ok(userVipService.getEvents(id));
    }

    @PostMapping("register")
    public ResponseEntity<Long> register(@RequestBody  UsrVp usrVp){
        return ResponseEntity.ok(userVipService.register(usrVp));
    }

    @GetMapping("login")
    public ResponseEntity<Long> login(@RequestParam String email,String password){
        return ResponseEntity.ok(userVipService.login(email,password));
    }

}
