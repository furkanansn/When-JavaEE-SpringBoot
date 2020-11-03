package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.User;
import lombok.Data;

/**
 * When Created by furkanansin on Nov, 2020
 */
@Data
public class CommentIDto {
    private Long id;
    private String username;
    private String firebaseId;

}
