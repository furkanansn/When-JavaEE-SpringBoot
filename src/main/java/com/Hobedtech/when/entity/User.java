package com.Hobedtech.when.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;


@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username", columnList = "uname")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname", length = 100, unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "pwd", length = 200)
    private String password;

    @Column(name = "image")
    private String image;
    @JsonIgnore
    @Email
    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name_surname", length = 200)
    private String nameSurname;

    @Column(name = "bio", length = 100)
    private String bio;

    @Column(name = "firebase_id", length = 100)
    private String firebaseId;

    @Column(name = "school", length = 100)
    private String school;

    @Column(name = "age")
    private Integer age;


    @Column(name = "location")
    private String location;


    @Column(name = "gender")
    private String gender;
    @JsonIgnore
    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<Events> events;


    @JsonIgnore
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<CheckIn> checkIns;

    private static final int EXPIRATION = 60 * 24;
    @JsonIgnore
    @Column(name="token")
    private String token;
    @JsonIgnore
    @Column(name="created_date")
    private Date createdDate;
    @JsonIgnore
    @Column(name="expiry_date")
    private Date expiryDate;
    @JsonIgnore
    @Column(name="active")
    private Boolean active;


    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        // calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        // calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }



}







