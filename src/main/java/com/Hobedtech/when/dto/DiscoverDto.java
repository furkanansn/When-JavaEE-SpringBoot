package com.Hobedtech.when.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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


}
