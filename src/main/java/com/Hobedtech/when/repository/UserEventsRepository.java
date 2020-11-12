package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.EventsUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface UserEventsRepository extends JpaRepository<EventsUsers, EventsUsers.EventsUserId> {
        EventsUsers findByEventsUserId_EventIdAndEventsUserId_UserId(Long eventId,Long userId);
}
