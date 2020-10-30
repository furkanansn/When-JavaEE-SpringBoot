package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface DiscoverRepository extends JpaRepository<Events,Long> {
    @Query(value = "select * from events \n" +
            "inner join usr_vp on events.usr_vp_id = usr_vp.id\n" +
            "where city = :cityName\n" +
            "AND\n" +
            "cast(date AS text) like  %:date%",nativeQuery = true)
    List<Events> findAll(@Param("cityName")String cityName, @Param("date")String date);
}
