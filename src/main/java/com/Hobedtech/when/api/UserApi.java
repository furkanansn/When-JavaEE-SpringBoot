package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.*;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.service.impl.UserServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class UserApi {
    private final UserServiceImpl userServiceImpl;

    public UserApi(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PutMapping("/user-update")
    public ResponseEntity<UserUpdateDto> userUpdate(@RequestBody UserUpdateDto userDto){
        return ResponseEntity.ok(userServiceImpl.save(userDto));
    }

    @GetMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam Long id, String password, String newPassword){
        return ResponseEntity.ok(userServiceImpl.changePasswordByUser(id,password,newPassword));
    }



    @GetMapping("/get-my-profile")
    public ResponseEntity<UserDto> getMyProfileById(@RequestParam Long id){
        UserDto generalUserInfo = userServiceImpl.getById(id);
        Integer count =  userServiceImpl.countFriends(id);
        generalUserInfo.setFriendCount(count);
        return ResponseEntity.ok(generalUserInfo);
    }

    @GetMapping("/get-user-profile")
    public ResponseEntity<OtherUserDto> getUserProfileById(@RequestParam Long userId, @RequestParam Long otherUserId){
        OtherUserDto generalUserInfo = userServiceImpl.getByIdOtherUser(userId);
        Integer count = userServiceImpl.countFriends(userId);
        String friendStuation = userServiceImpl.isFriend(userId,otherUserId);
        generalUserInfo.setFriendCount(count);
        generalUserInfo.setFriendStituation(friendStuation);
        return ResponseEntity.ok(generalUserInfo);
    }
    @GetMapping("/get-user-profile/check-in")
    public ResponseEntity<ProfileCheckInDto> getUserProfileCheckIn(@RequestParam Long id){
        ProfileCheckInDto profileCheckInDto = userServiceImpl.getByIdProfileCheckIn(id);
        return ResponseEntity.ok(profileCheckInDto);
    }
    @GetMapping("/get-user-profile/fav")
    public ResponseEntity<ProfileFavDto> getUserProfileFav(@RequestParam Long id){
        ProfileFavDto profileFavDto = userServiceImpl.getByIdProfileFav(id);
        return ResponseEntity.ok(profileFavDto);
    }
    @GetMapping("/get-user-profile/venue")
    public ResponseEntity<UserVipDto> getUserProfileVenue(@RequestParam Long id){
        UserVipDto profileVenueDtoDto = userServiceImpl.getByIdProfileVenue(id);
        System.out.println(profileVenueDtoDto);
        return ResponseEntity.ok(profileVenueDtoDto);
    }
}
