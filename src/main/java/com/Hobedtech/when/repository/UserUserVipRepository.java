package com.Hobedtech.when.repository;

import com.Hobedtech.when.entity.UserUserVip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Nov, 2020
 */
public interface UserUserVipRepository extends JpaRepository<UserUserVip,Long> {

}
