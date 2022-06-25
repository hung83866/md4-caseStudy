package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.model.Post;

import java.util.List;

public interface ICommentService {

    Comment findById(Long id);

    List<Comment> findAllByPostId(Long idPost);

    Comment save(Long postId, Comment comment);
}
