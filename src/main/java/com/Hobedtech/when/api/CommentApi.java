package com.Hobedtech.when.api;

import com.Hobedtech.when.PostDto.CommentDeleteDto;
import com.Hobedtech.when.PostDto.CommentPostDto;
import com.Hobedtech.when.dto.CommentDto;
import com.Hobedtech.when.entity.Comment;
import com.Hobedtech.when.service.impl.CommentServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * When Created by furkanansin on Nov, 2020
 */
@RestController
@RequestMapping("<API_KEY=>"+ApiPaths.NORMALUSERAPIKEY+ApiPaths.CommentCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class CommentApi {
    private final CommentServiceImpl commentServiceImpl;

    public CommentApi(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByEventId(@RequestParam Long eventId){
        return ResponseEntity.ok(commentServiceImpl.getByEventId(eventId));
    }
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentPostDto comment){
        return ResponseEntity.ok(commentServiceImpl.add(comment));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestBody CommentDeleteDto comment){
        return ResponseEntity.ok(commentServiceImpl.delete(comment));
    }
}
