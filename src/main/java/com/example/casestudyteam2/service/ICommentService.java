package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.model.Post;

import java.util.List;

public interface ICommentService {
    Iterable<Comment> findAll();

    Comment findById(Long id);

    void save(Comment comment);

    void remove(Long id);

    Iterable<Comment> findAllByPost(Post post);
}
