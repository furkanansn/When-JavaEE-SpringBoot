package com.Hobedtech.when.dto;


import com.Hobedtech.when.entity.UsrVp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseInitializeDto {
    private Long id;
    private String title;
    private String eventImagePath;
    private String city;
    private Date date;
    Set<UserEventDto> users;
    private Set<UsrVp> userVips;
}
