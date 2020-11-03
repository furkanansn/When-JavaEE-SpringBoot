package com.Hobedtech.when.dto;

import com.Hobedtech.when.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * When Created by furkanansin on Nov, 2020
 */
@Data
public class CommentDto {
   // comment.comment,comment.created_at,comment.updated_at,uname, firebase_id
    private String comment;
    private Date created_at;
    private Date updated_at;
    private CommentIDto users;
}
