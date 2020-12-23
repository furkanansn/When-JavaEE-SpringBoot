package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.UsrVp;
import lombok.Data;

import java.util.Date;

/**
 * when Created by furkanansin on Dec, 2020
 */
@Data
public class EventDto {
    private String title;
    private String eventImagePath;
    private String city;
    private Date date;
    private UsrVp userVips;
}
