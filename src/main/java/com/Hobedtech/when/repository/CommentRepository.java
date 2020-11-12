package com.Hobedtech.when.repository;

import com.Hobedtech.when.dto.CommentDto;
import com.Hobedtech.when.entity.Comment;
import com.Hobedtech.when.entity.FriendsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * When Created by furkanansin on Nov, 2020
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByEvents_IdAndStatus(Long events_id, String status);
}
