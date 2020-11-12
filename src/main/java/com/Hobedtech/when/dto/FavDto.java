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
public class FavDto {
    private Long id;
    private String username;
    private String email;
    private String nameSurname;
    private String bio;
    private String photo;
    private String school;
    private Integer age;
    private String gender;
}
