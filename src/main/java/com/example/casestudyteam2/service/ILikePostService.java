package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.LikePost;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILikePostService {
    List<LikePost> findAll();
    LikePost save (LikePost likePost);
    List<LikePost> findLikePostByPostIdAndUserId(Long PostId, Long userId);
    Integer countLikeOfPostByPostId(Long postId);
    void deleteLikePost(LikePost likePost);
}
