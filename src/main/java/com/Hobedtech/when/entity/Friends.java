package com.Hobedtech.when.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "friends",indexes = {@Index(name = "idx_friend_one",columnList = "friend_one")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friends extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FriendsStatus friendsStatus;

    @Column(name = "friend_one")
    private Long friendOne;

    @Column(name = "friend_two")
    private Long friendTwo;


}
