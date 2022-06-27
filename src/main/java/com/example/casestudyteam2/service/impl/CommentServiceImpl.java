package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.repository.ICommentRepository;
import com.example.casestudyteam2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        if (commentRepository.findById(id).isPresent()){
            return commentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }


    @Override
    public Iterable<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
