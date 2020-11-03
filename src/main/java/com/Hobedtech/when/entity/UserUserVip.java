package com.Hobedtech.when.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UserUserVip",indexes = {@Index(name = "idx_user_id",columnList = "user_id"),@Index(name = "idx_user_vip_id",columnList  ="user_vip_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUserVip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FriendsStatus friendsStatus;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_vip_id")
    private Long UserVipId;



}
