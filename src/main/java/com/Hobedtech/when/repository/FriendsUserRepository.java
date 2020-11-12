package com.Hobedtech.when.repository;


import com.Hobedtech.when.entity.Friends;
import com.Hobedtech.when.entity.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendsUserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from friends inner join users on friends.friend_two = users.id\n" +
            "\t\t\t\t\twhere friends.friend_one = :userId and friends.status = 'ACTIVE'",nativeQuery = true)
    List<User> getFriendById(@Param("userId") Long userId);

    @Query(value = "select * from friends inner join users on friends.friend_one = users.id\n" +
            "\t\t\t\t\twhere friends.friend_two = :userId and friends.status = 'ACTIVE'",nativeQuery = true)
    List<User> getFriendByIdPers(@Param("userId") Long userId);



    @Query(value = "select * from friends inner join users on friends.friend_two = users.id\n" +
            "\t\t\t\t\twhere friends.friend_one = :userId and friends.status = 'ACTIVE' and uname like :name%",nativeQuery = true)
    List<User> getFriendByIdAndUsername(@Param("userId") Long userId,@Param("name") String username);

    @Query(value = "select * from friends inner join users on friends.friend_one = users.id\n" +
            "\t\t\t\t\twhere friends.friend_two = :userId and friends.status = 'ACTIVE' and uname like :name%",nativeQuery = true)
    List<User> getFriendByIdPersAndUserName(@Param("userId") Long userId,@Param("name") String username);
}
