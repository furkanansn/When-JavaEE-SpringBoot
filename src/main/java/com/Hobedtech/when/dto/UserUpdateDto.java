package com.Hobedtech.when.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private Long id;
    private String nameSurname;
    private String bio;
    private String firebaseId;
    private String school;
    private Integer age;
    private String latitude;
    private String longitude;
    private String gender;
}