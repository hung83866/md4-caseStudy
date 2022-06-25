package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.repository.ICommentRepository;
import com.example.casestudyteam2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;

    @Override
    public Comment findById(Long id) {
        if (commentRepository.findById(id).isPresent()){
            return commentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Comment> findAllByPostId(Long idPost) {
        return null;
    }

    @Override
    public Comment save(Long postId, Comment comment) {
        return null;
    }
}
