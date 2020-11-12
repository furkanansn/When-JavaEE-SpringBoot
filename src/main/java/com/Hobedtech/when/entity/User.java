package com.Hobedtech.when.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Min(message = "Kullanıcı adı en az 3 karakterden oluşmalıdır", value = 3)
    @Column(name = "uname", length = 100, unique = true)
    private String username;

    @Min(message = "Parola en az 8 karakterden oluşmalıdır",value = 8)
    @Column(name = "pwd", length = 200)
    private String password;

    @NotEmpty(message = "E-mail boş olmamalıdır")
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

    @Column(name="active")
    private Boolean active;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "gender")
    private String gender;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<Events> events;

    @JsonIgnore
    @ManyToMany(mappedBy = "usersCheckIn")
    private Set<Events> eventsCheckIn;

    @JsonIgnore
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<Comment> comments;

    private static final int EXPIRATION = 60 * 24;

    @Column(name="token")
    private String token;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="expiry_date")
    private Date expiryDate;

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        // calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        // calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }



}







