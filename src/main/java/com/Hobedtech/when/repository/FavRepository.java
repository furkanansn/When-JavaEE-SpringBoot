package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface FavRepository extends JpaRepository<User,Long> {
    @Query(value = "select users.id,users.created_at,users.updated_at,users.age,users.bio,users.firebase_id,users.name_surname,users.pwd,users.phone,users.school,users.uname,users.email,users.gender,users.latitude,users.longitude,users.active,users.created_date,users.expiry_date,users.token from events_users inner join events on events_users.events_id = id\n" +
            "inner join users on users.id = events_users.users_id\n" +
            "where events_users.events_id = :eventId and events_users.fav_status = 'ACTIVE'",nativeQuery = true)
    List<User> findAll(@Param("eventId") Long eventId);

    @Query(value = "select * from events_users inner join events on events_users.events_id = id\n" +
            "inner join users on users.id = events_users.users_id\n" +
            "where events_users.events_id = :eventId and users.uname like :userName% and events_users.fav_status = 'ACTIVE'",nativeQuery = true)
    List<User> findAllSearch(@Param("eventId")Long eventId,@Param("userName")String userName);
}
