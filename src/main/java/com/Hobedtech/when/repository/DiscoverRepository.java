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
            "inner join usr_vp on events.user_vips_id = usr_vp.id\n" +
            "where city = :cityName\n" +
            "AND\n" +
            "cast(date AS text) like  %:date%", nativeQuery = true)
    List<Events> findAll(@Param("cityName") String cityName, @Param("date") String date);


    @Query(value = "select Count(users_id) from events \n" +
            "            inner join usr_vp on events.user_vips_id = usr_vp.id\n" +
            "\t\t\tinner join events_users as i on events.id = i.events_id\n" +
            "\t\t\tinner join users on users.id = i.users_id\n" +
            "\t\t\twhere city = :cityName\n" +
            "            AND\n" +
            "            cast(date AS text) like  %:date%\n" +
            "\t\t\t\n" +
            "          ", nativeQuery = true)
    Integer countAll(@Param("cityName") String cityName, @Param("date") String date);

//where date > now()
    @Query(value = "select * from events",nativeQuery = true)
    List<Events> getEventsForCyprus();
}