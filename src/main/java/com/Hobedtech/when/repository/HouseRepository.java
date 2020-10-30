package com.Hobedtech.when.repository;


import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.util.DateCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface HouseRepository extends JpaRepository<Events,Long> {
    @Query(value ="select * from users\n" +
            "    inner join users_usr_vps ON users_usr_vps.users_id = users.id\n" +
            "    inner join usr_vp on usr_vp.id = users_usr_vps.usr_vps_id\n" +
            "    inner join events on events.usr_vp_id = usr_vp.id\n" +
            "    where users_usr_vps.users_id = :user_id\n" +
            "    AND\n" +
            "    cast(date AS text) like  %:dateCurrent%",nativeQuery = true)
    List<Events> findAll(@Param("dateCurrent")String dateCurrent,@Param("user_id") Long user_id);

    @Query(value = "select * from users where cast(date AS text) like  %:dateCurrent%",nativeQuery = true)
    List<Events> findAllAnother(@Param("dateCurrent")String dateCurrent);

}

