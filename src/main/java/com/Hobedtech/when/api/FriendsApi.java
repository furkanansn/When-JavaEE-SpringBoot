package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.entity.Friends;
import com.Hobedtech.when.service.impl.FriendsServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping("<API_KEY=>"+ApiPaths.NORMALUSERAPIKEY+ApiPaths.FriendsCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class FriendsApi {
    private final FriendsServiceImpl friendsServiceImpl;

    public FriendsApi(FriendsServiceImpl friendsServiceImpl) {
        this.friendsServiceImpl = friendsServiceImpl;
    }
    @GetMapping
    public ResponseEntity<List<FriendDto>> getFriends(@RequestParam Long userId){
        return ResponseEntity.ok(friendsServiceImpl.getFriendsById(userId));
    }
    @GetMapping("/search")
    public ResponseEntity<List<FriendDto>> searchFriends(@RequestParam Long userId,@RequestParam String userName){
    return ResponseEntity.ok(friendsServiceImpl.getFriendsByIdAndName(userId,userName));
    }

    @PostMapping
    public ResponseEntity<Boolean> addFriend(@RequestBody Friends friends){
        return ResponseEntity.ok(friendsServiceImpl.save(friends));
    }

    @PutMapping
    public ResponseEntity<Friends> updateFriend(@RequestBody Friends friends){
        return ResponseEntity.ok(friendsServiceImpl.update(friends));
    }

    @DeleteMapping
    public ResponseEntity<Friends> deleteFriend(@RequestBody Friends friends){
        return ResponseEntity.ok(friendsServiceImpl.delete(friends));
    }


}
