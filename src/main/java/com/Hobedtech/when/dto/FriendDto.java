package com.Hobedtech.when.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by temelt on 4.02.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class FriendDto {
    @ApiModelProperty(required = true,value = "ID")
    private Long id;
    private String username;
    private String nameSurname;
    private String bio;
    private String firebaseId;
    private String school;
    private String image;
    private Integer age;
    private String latitude;
    private String longitude;
    private String gender;
    private Integer friendCount;
    private String friendStituation;

}
