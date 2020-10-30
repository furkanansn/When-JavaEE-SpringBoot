package com.Hobedtech.when.repository;


import com.Hobedtech.when.entity.UsrVp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserVipRepository extends JpaRepository<UsrVp,Long> {
    @Query(value = "select Count(usr_vps_id) from users_usr_vps as i inner join users on users.id = i.users_id\n" +
            "inner join usr_vp on usr_vp.id = i.usr_vps_id where  usr_vps_id = :vpid",nativeQuery = true)
    Integer countFollowers(@Param("vpid") Long id);

}
