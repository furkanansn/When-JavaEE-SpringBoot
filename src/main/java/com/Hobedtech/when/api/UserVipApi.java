package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.ProfileUserDtoUsers;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.service.impl.UserVipServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.UserVipCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class UserVipApi {
    private final UserVipServiceImpl userVipServiceImpl;

    public UserVipApi(UserVipServiceImpl userVipServiceImpl) {
        this.userVipServiceImpl = userVipServiceImpl;
    }

    @GetMapping("/get-user-vip-profile")
    public ResponseEntity<UserVipDto> getUserVipProfile(@RequestParam Long id){
        UserVipDto userVipDto = userVipServiceImpl.getById(id);
        Integer count = userVipServiceImpl.follwersCount(id);
        userVipDto.setFollowersCount(count);
        return ResponseEntity.ok(userVipDto);
    }
    @GetMapping("/get-user-vip-profile/followers")
    public ResponseEntity<ProfileUserDtoUsers> getUserVipProfileFollowers(@RequestParam Long id){
        return ResponseEntity.ok(userVipServiceImpl.getByIdFollowers(id));
    }

}
