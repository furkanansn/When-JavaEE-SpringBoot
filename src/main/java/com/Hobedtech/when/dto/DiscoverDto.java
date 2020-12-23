package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Discover Data Transfer Object")
public class DiscoverDto {
    private Long id;
    private String title;
    private String eventImagePath;
    private String city;
    private Date date;
    Set<UserEventDto> users;
    private UsrVp userVips;


}
