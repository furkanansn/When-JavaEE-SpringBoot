package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * when Created by furkanansin on Dec, 2020
 */
public interface EventRepository extends JpaRepository<Events, Long> {
}
