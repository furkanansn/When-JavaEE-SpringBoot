package com.Hobedtech.when.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherUserDto {
    private Long id;
    private String username;
    private String nameSurname;
    private String bio;
    private String firebaseId;
    private String school;
    private Integer age;
    private String latitude;
    private String longitude;
    private String gender;
    private Integer friendCount;
    private String friendStituation;
}
