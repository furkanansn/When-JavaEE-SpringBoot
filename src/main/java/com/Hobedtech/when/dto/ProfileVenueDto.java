package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.UsrVp;
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
public class ProfileVenueDto {
    private Set<UsrVp> usrVps;
}
