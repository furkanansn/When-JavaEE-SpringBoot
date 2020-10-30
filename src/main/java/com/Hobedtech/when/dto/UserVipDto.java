package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVipDto {
    private Long id;
    private String username;
    private String photo;
    private String bio;
    private String latitude;
    private String longitude;
    private String confirmedAccount;
    private Integer followersCount;
    private Set<Events> events;


}
