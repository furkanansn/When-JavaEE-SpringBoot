package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * when Created by furkanansin on Dec, 2020
 */
public interface EventRepository extends JpaRepository<Events, Long> {
    @Query(value ="select * from events where user_vips_id = :id",nativeQuery = true)
    List<Events> getEventsVenue(@Param("id")Long id);
}
