package com.Hobedtech.when.dto;

import java.sql.Date;

import lombok.Data;

/**
 * when Created by furkanansin on Dec, 2020
 */
@Data
public class EventDto {
    private String title;
    private String city;
    private Date date;
    private String image;
    private String imageType;
    private Long userVipId;
}
