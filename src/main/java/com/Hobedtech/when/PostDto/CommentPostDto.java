package com.Hobedtech.when.PostDto;

import lombok.Data;

/**
 * When Created by furkanansin on Nov, 2020
 */
@Data
public class CommentPostDto {
    private String comment;
    private Long event_id;
    private Long user_id;
}
