package com.Hobedtech.when.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Events extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "event_image_path",length = 200)
    private String eventImagePath;

    @Column(name = "numberOfViews")
    private Long numberOfViews;

    @Column(name = "city",length = 50)
    private String city;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.LAZY)
    Set<User> users;



    @JsonIgnore
    @ManyToOne
    private UsrVp userVips;

    @JsonIgnore
    @OneToMany(mappedBy = "events",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<Comment> comments;


    @JsonIgnore
    @OneToMany(mappedBy = "events",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Set<CheckIn> checkIns;


}
