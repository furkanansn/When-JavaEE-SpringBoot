package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.UserUserVip;
import com.Hobedtech.when.service.impl.UserUserVipServiceImpl;
import com.Hobedtech.when.service.impl.UserVipServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.UserVipCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class UserVipApi {
    private final UserVipServiceImpl userVipServiceImpl;
    private final UserUserVipServiceImpl userUserVipService;

    public UserVipApi(UserVipServiceImpl userVipServiceImpl, UserUserVipServiceImpl userUserVipService) {
        this.userVipServiceImpl = userVipServiceImpl;
        this.userUserVipService = userUserVipService;
    }

    @GetMapping("/get-user-vip-profile")
    public ResponseEntity<UserVipDto> getUserVipProfile(@RequestParam Long id){
        UserVipDto userVipDto = userVipServiceImpl.getById(id);
        Integer count = userVipServiceImpl.follwersCount(id);
        userVipDto.setFollowersCount(count);
        return ResponseEntity.ok(userVipDto);
    }
    @GetMapping("/get-user-vip-profile/followers")
    public ResponseEntity<List<FriendDto>> getUserVipProfileFollowers(@RequestParam Long id){
        return ResponseEntity.ok(userVipServiceImpl.getByIdFollowers(id));
    }
    @PostMapping
    public ResponseEntity<UserUserVip> FollowRequest(@RequestBody UserUserVip userUserVip){
        return ResponseEntity.ok(userUserVipService.follow(userUserVip));
    }

    @DeleteMapping
    public ResponseEntity<UserUserVip> Unfollow(@RequestBody UserUserVip userUserVip){
        return ResponseEntity.ok(userUserVipService.unFollow(userUserVip));
    }


}
