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
public class SearchDto {
    private Long id;
    private String username;
    private String photo;
    private String bio;
    private String confirmedAccount;



    /*
     "createdAt" : null,
  "updatedAt" : null,
  "id" : 1,
  "username" : "qwe",
  "password" : null,
  "email" : null,
  "phone" : null,
  "nameSurname" : null,
  "bio" : null,
  "firebaseId" : null,
  "school" : null,
  "age" : 22,
  "gender" : null
}, {
  "createdAt" : null,
  "updatedAt" : null,
  "id" : 1,
  "name" : "qwe",
  "email" : null,
  "phone" : null,
  "venueImagePath" : null,
  "bio" : "asdasdasd",
  "active" : null,
  "iframe" : null
     */
}
