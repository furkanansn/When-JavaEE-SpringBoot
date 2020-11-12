package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Oct, 2020
 */
public interface SearchVenueRepository extends JpaRepository<UsrVp,Long> {
    List<UsrVp> findUsrVpsByUsernameStartsWith(String venueName);
}
