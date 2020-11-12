package com.Hobedtech.when.service;

import com.Hobedtech.when.PostDto.CommentDeleteDto;
import com.Hobedtech.when.PostDto.CommentPostDto;
import com.Hobedtech.when.dto.CommentDto;
import com.Hobedtech.when.entity.Comment;

import java.util.List;

/**
 * When Created by furkanansin on Nov, 2020
 */
public interface CommentService {
    String add(CommentPostDto comment);
    String delete(Long commentId,Long userId);
    List<CommentDto> getByEventId(Long eventId);
}
