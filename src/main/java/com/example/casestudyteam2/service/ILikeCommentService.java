package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.LikeComment;

import java.util.List;


public interface ILikeCommentService {
    List<LikeComment> findAll();
    LikeComment findById(Long id);
    LikeComment save (LikeComment likeComment);
    void deleteById(Long id);
    List<LikeComment> findLikeCommentByCommentIdAndUserId(Long commentId, Long userId);
    Integer countLikeOfCommentByCommentId(Long CommentId);
    void deleteLikeComment(LikeComment likeComment);
}
