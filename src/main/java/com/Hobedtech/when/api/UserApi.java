package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.*;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.service.impl.UserServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
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
    public ResponseEntity<GeneralResponse> userUpdate(@RequestBody UserUpdateDto userDto) throws IOException {
        return new GeneralApi().sendResponse(new GeneralResponse(true,userServiceImpl.save(userDto),null));

    }

    @GetMapping("/change-password")
    public ResponseEntity<GeneralResponse> changePassword(@RequestParam Long id, String password, String newPassword){
        return new GeneralApi().sendResponse(new GeneralResponse(true,userServiceImpl.changePasswordByUser(id,password,newPassword),null));

    }



    @GetMapping("/get-my-profile")
    public ResponseEntity<GeneralResponse> getMyProfileById(@RequestParam Long id){
        UserDto generalUserInfo = userServiceImpl.getById(id);
        Object friends =  userServiceImpl.getFriends(id);
        generalUserInfo.setFrien(friends);
        return new GeneralApi().sendResponse(new GeneralResponse(true,generalUserInfo,null));

    }

    @GetMapping("/get-user-profile")
    public ResponseEntity<GeneralResponse> getUserProfileById(@RequestParam Long userId, @RequestParam Long otherUserId){
        OtherUserDto generalUserInfo = userServiceImpl.getByIdOtherUser(userId);
        Object friends =  userServiceImpl.getFriends(otherUserId);
        String friendStuation = userServiceImpl.isFriend(userId,otherUserId);
        generalUserInfo.setFrien(friends);
        generalUserInfo.setFriendStituation(friendStuation);

        return new GeneralApi().sendResponse(new GeneralResponse(true,generalUserInfo,null));

    }
    @GetMapping("/get-user-profile/check-in")
    public ResponseEntity<GeneralResponse> getUserProfileCheckIn(@RequestParam Long id){
        ProfileCheckInDto profileCheckInDto = userServiceImpl.getByIdProfileCheckIn(id);
        return new GeneralApi().sendResponse(new GeneralResponse(true,profileCheckInDto,null));
    }
    @GetMapping("/get-user-profile/fav")
    public ResponseEntity<GeneralResponse> getUserProfileFav(@RequestParam Long id){
        ProfileFavDto profileFavDto = userServiceImpl.getByIdProfileFav(id);

        return new GeneralApi().sendResponse(new GeneralResponse(true,profileFavDto,null));

    }
    @GetMapping("/get-user-profile/venue")
    public ResponseEntity<GeneralResponse> getUserProfileVenue(@RequestParam Long id){
        UserVipDto profileVenueDtoDto = userServiceImpl.getByIdProfileVenue(id);
        System.out.println(profileVenueDtoDto);
        return new GeneralApi().sendResponse(new GeneralResponse(true,profileVenueDtoDto,null));
    }
}
