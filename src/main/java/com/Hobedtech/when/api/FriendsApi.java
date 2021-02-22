package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FriendShipDto;
import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.entity.Friends;
import com.Hobedtech.when.service.impl.FriendsServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.FriendsCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class FriendsApi {
    private final FriendsServiceImpl friendsServiceImpl;

    public FriendsApi(FriendsServiceImpl friendsServiceImpl) {
        this.friendsServiceImpl = friendsServiceImpl;
    }
    @GetMapping
    public ResponseEntity<GeneralResponse> getFriends(@RequestParam Long userId){
        return new GeneralApi().sendResponse(new GeneralResponse(true,friendsServiceImpl.getFriendsById(userId),null));

    }
    @GetMapping("/search")
    public ResponseEntity<GeneralResponse> searchFriends(@RequestParam Long userId, @RequestParam String userName){
        return new GeneralApi().sendResponse(new GeneralResponse(true,friendsServiceImpl.getFriendsByIdAndName(userId,userName),null));

    }

    @PostMapping
    public ResponseEntity<GeneralResponse> addFriend(@RequestBody FriendShipDto friends){
        return new GeneralApi().sendResponse(new GeneralResponse(true,friendsServiceImpl.save(friends),null));

    }

    @PutMapping
    public ResponseEntity<GeneralResponse> updateFriend(@RequestBody FriendShipDto friends){
        return new GeneralApi().sendResponse(new GeneralResponse(true,friendsServiceImpl.update(friends),null));
    }

    @DeleteMapping
    public ResponseEntity<GeneralResponse> deleteFriend(@RequestBody FriendShipDto friends){
        friendsServiceImpl.delete(friends);

        return new GeneralApi().sendResponse(new GeneralResponse(true,true,null));

    }


}
