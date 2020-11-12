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
    @Query(value ="select * from events \n" +
            "            inner join usr_vp on events.usr_vp_id = usr_vp.id\n" +
            "            where cast(date AS text) like  %:dateCurrent%",nativeQuery = true)
    List<Events> findAll(@Param("dateCurrent")String dateCurrent);



}

