package com.Hobedtech.when.service;

import com.Hobedtech.when.dto.*;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.util.TPage;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * Created by temelt on 4.02.2019.
 */
public interface UserService {

    UserUpdateDto save(UserUpdateDto user) throws IOException;

    UserDto getById(Long id);

    OtherUserDto getByIdOtherUser(Long id);

    ProfileCheckInDto getByIdProfileCheckIn(Long id);

    ProfileFavDto getByIdProfileFav(Long id);

    UserVipDto getByIdProfileVenue(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);

    User sendAgain(Long id);

    User forgotPassword(String email);

    String changePassword(Long id);

    String changePasswordByUser(Long id , String password,String newPassword);

    Integer countFriends(Long userId);

    String isFriend(Long userId, Long otherUserId);
}
