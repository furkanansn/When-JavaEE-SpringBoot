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
/*
    @Query(value ="select * from users\n" +
            "    inner join user_user_vip ON user_user_vip.user_id = users.id\n" +
            "    inner join usr_vp on usr_vp.id = user_user_vip.user_vip_id\n" +
            "    inner join events on events.usr_vp_id = usr_vp.id\n" +
            "    where user_user_vip.user_id = :user_id\n" +
            "or 1 = 1" +
            "    AND\n" +
            "    cast(events.date AS text) like  %:dateCurrent%",nativeQuery = true)
    List<Events> findAll(@Param("dateCurrent")String dateCurrent,@Param("user_id") Long user_id);
*/


    @Query(value ="select * from events\n" +
            "    where cast(events.date AS text) like  %:dateCurrent%",nativeQuery = true)
    List<Events> findAll(@Param("dateCurrent")String dateCurrent);



}

