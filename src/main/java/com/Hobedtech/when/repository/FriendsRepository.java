package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface FriendsRepository extends JpaRepository<Friends,Long> {
    @Query(value = "select * from friends where friend_one = :friend_one and friend_two = :friend_two or friend_one = :o and friend_two = :t and status <> 'DEACTIVE'",nativeQuery = true)
    Friends findTopBy(@Param("friend_one") Long friend_one, @Param("friend_two") Long friend_two,@Param("t") Long t, @Param("o") Long o);



}
