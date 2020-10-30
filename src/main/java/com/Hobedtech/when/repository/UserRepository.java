package com.Hobedtech.when.repository;


import com.Hobedtech.when.entity.Friends;
import com.Hobedtech.when.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);




    @Query(value = "select Count(friends.friend_one) from friends inner join users on friends.friend_two = users.id\n" +
            "\t\t\t\t\twhere friends.friend_one = :userId and friends.status = 'ACTIVE'",nativeQuery = true)
    Integer getCountFriendById(@Param("userId") Long userId);

    @Query(value = "select Count(friends.friend_two) from friends inner join users on friends.friend_one = users.id\n" +
            "\t\t\t\t\twhere friends.friend_two = :userId and friends.status = 'ACTIVE'",nativeQuery = true)
    Integer getCountFriendByIdPers(@Param("userId") Long userId);

    @Query(value = "select friends.status from friends inner join users on friends.friend_two = users.id\n" +
            "\t\t\t\t\twhere friends.friend_one = :userId and friends.friend_two = :otherUserId and friends.status<> 'DEACTIVE' ",nativeQuery = true)
    String isFriendFriendById(@Param("userId") Long userId, @Param("otherUserId") Long otherUserId);

    @Query(value = "select friends.status from friends inner join users on friends.friend_one = users.id\n" +
            "\t\t\t\t\twhere friends.friend_two = :userId and  friends.friend_one = :otherUserId and friends.status<> 'DEACTIVE'",nativeQuery = true)
    String isFriendFriendByIdPers(@Param("userId") Long userId,@Param("otherUserId") Long otherUserId);



}
