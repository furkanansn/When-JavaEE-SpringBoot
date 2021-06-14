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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "venue_name",unique = true,length = 255)
    private String username;

    @Column(name = "email", length = 255,unique = true)
    private String email;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "venue_image_path", length = 255)
    private String photo;

    @Column(name = "bio", length = 255)
    private String bio;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name ="latitude",length = 255)
    private Double latitude;

    @Column(name ="longitude",length = 255)
    private Double longitude;

    @Column(name = "role",length = 255)
    private String role;

    @Column(name = "confirmed",length = 255)
    private String confirmedAccount;


    @JsonIgnore
    @OneToMany(mappedBy = "userVips", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<Events> events;

}