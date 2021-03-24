package com.Hobedtech.when.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.Hobedtech.when.entity.CheckIn;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String nameSurname;
    private String bio;
    private String firebaseId;
    private String image;
    private String school;
    private Integer age;
    private String latitude;
    private String longitude;
    private String gender;
    private Integer friendCount;
    private Set<CheckIn> checkIns;
    
}
