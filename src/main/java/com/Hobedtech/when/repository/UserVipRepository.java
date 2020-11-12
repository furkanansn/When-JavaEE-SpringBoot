package com.Hobedtech.when.repository;


import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserVipRepository extends JpaRepository<UsrVp,Long> {
    @Query(value = "select Count(i.id) from user_user_vip as i inner join users on users.id = i.user_id\n" +
            "            inner join usr_vp on usr_vp.id = i.user_vip_id where  user_vip_id = :vpid",nativeQuery = true)
    Integer countFollowers(@Param("vpid") Long id);

    @Query(value = "select * from user_user_vip as i inner join users on users.id = i.user_id\n" +
            "inner join usr_vp on usr_vp.id = i.user_vip_id where users.id = :userId and i.status = 'ACTIVE'",nativeQuery = true)
    UsrVp getVenueForProfile(@Param("userId") Long userId);



}
