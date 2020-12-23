package com.Hobedtech.when.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsrVp extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "venue_name",unique = true)
    private String username;

    @Column(name = "email", length = 30,unique = true)
    private String email;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "venue_image_path", length = 100)
    private String photo;

    @Column(name = "bio", length = 100)
    private String bio;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "iframe", length = 100)
    private String iFrame;

    @Column(name = "location")
    private String location;

    @Column(name = "role")
    private String role;

    @Column(name = "confirmed")
    private String confirmedAccount;


    @JsonIgnore
    @OneToMany(mappedBy = "userVips", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<Events> events;

}