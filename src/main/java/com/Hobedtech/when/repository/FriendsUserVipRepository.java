package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Nov, 2020
 */
public interface FriendsUserVipRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user_user_vip  inner join users on users.id = user_user_vip.user_id\n" +
            "        inner join usr_vp on usr_vp.id = user_user_vip.user_vip_id where usr_vp.id = :userId and user_user_vip.status = 'ACTIVE'",nativeQuery = true)
    List<User> getfollowersForProfile(@Param("userId") Long userId);
}
