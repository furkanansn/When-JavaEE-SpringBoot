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
    private String phone;
    private String username;

}
