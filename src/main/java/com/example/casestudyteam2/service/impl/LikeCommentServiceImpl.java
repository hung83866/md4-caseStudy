package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.LikeComment;
import com.example.casestudyteam2.repository.ILikeCommentRepository;
import com.example.casestudyteam2.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeCommentServiceImpl implements ILikeCommentService {
    @Autowired
    ILikeCommentRepository likeCommentRepository;


    @Override
    public List<LikeComment> findAll() {
        return likeCommentRepository.findAll();
    }

    @Override
    public LikeComment findById(Long id) {
        return null;
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return likeCommentRepository.save(likeComment);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<LikeComment> findLikeCommentByCommentIdAndUserId(Long commentId, Long userId) {
        return likeCommentRepository.findLikeCommentByCommentIdAndUserId(commentId, userId);
    }

    @Override
    public Integer countLikeOfCommentByCommentId(Long commentId) {
        return likeCommentRepository.countLikeOfCommentByCommentId(commentId);
    }

    @Override
    public void deleteLikeComment(LikeComment likeComment) {
        likeCommentRepository.delete(likeComment);
    }
}
