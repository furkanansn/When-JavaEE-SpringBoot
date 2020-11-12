package com.Hobedtech.when.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Entity(name="EventUserEntity")
@Table(name = "events_users")
public class EventsUsers implements Serializable {
    @EmbeddedId
    private EventsUserId eventsUserId;

    public EventsUserId getEventsUserId() {
        return eventsUserId;
    }

    public void setEventsUserId(EventsUserId eventsUserId) {
        this.eventsUserId = eventsUserId;
    }

    @JoinColumn(name = "events_id",insertable = false,updatable = false)
    @ManyToOne
    private Events events;

    @JoinColumn(name = "users_id",insertable = false,updatable = false)
    @ManyToOne
    private User user;



    private String favStatus;

    public EventsUsers() {}

    public EventsUsers(Events events, User user) {
        eventsUserId = new EventsUserId();
        eventsUserId.eventId = events.getId();
        eventsUserId.userId = user.getId();
        favStatus = "";
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    @Embeddable
    public static class EventsUserId implements Serializable{
        @Column(name = "users_id")
        public Long userId;
        @Column(name = "events_id")
        public Long eventId;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EventsUserId eventsUserId = (EventsUserId) o;
            return Objects.equals(eventId, eventsUserId.eventId) &&
                    Objects.equals(userId, eventsUserId.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(eventId, userId);
        }

    }

}
