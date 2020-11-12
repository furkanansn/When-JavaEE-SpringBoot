package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.PostDto.CommentDeleteDto;
import com.Hobedtech.when.PostDto.CommentPostDto;
import com.Hobedtech.when.dto.CommentDto;
import com.Hobedtech.when.entity.Comment;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.FriendsStatus;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.CommentRepository;
import com.Hobedtech.when.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * When Created by furkanansin on Nov, 2020
 */
@Service

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;

        this.modelMapper = modelMapper;
    }


    @Override
    public String add(CommentPostDto commentPostDto) {
        Comment comment = new Comment();
        comment.setStatus("ACTIVE");
        comment.setComment(commentPostDto.getComment());

        Events events = new Events();
        events.setId(commentPostDto.getEvent_id());
        comment.setEvents(events);

        User user = new User();
        user.setId(commentPostDto.getUser_id());
        comment.setUsers(user);
        commentRepository.save(comment);
        return "Yorum başarıyla eklendi";
    }

    @Override
    public String delete(Long commentId,Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        if(comment.getUsers().getId().equals(userId)){
            comment.setStatus("DEACTIVE");
            commentRepository.save(comment);
            return "Başarıyla silindi";
        }
        else{
            return "Size ait olmayan bir yorumu silemezsiniz";
        }

    }

    @Override
    public List<CommentDto> getByEventId(Long eventId) {
        List<Comment> comment = commentRepository.findAllByEvents_IdAndStatus(eventId,"ACTIVE");
        return Arrays.asList(modelMapper.map(comment,CommentDto[].class));
    }


}
