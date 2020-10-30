package com.Hobedtech.when.dto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.UsrVp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class UserDto {
    @ApiModelProperty(required = true,value = "ID")
    private Long id;
    private String username;
    private String nameSurname;
    private String bio;
    private String firebaseId;
    private String school;
    private Integer age;
    private String latitude;
    private String longitude;
    private String gender;
    private Integer friendCount;
}
