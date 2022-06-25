package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.LikePost;
import com.example.casestudyteam2.repository.ILikePostRepository;
import com.example.casestudyteam2.service.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikePostServiceImpl implements ILikePostService {
    @Autowired
    private ILikePostRepository likePostRepository;


    @Override
    public List<LikePost> findAll() {
        return likePostRepository.findAll();
    }


    @Override
    public LikePost save(LikePost likePost) {
        return likePostRepository.save(likePost);
    }


    @Override
    public List<LikePost> findLikePostByPostIdAndUserId(Long postId, Long userId) {
        return likePostRepository.findLikePostByPostIdAndUserId(postId, userId);
    }

    @Override
    public Integer countLikeOfPostByPostId(Long postId) {
        return likePostRepository.countLikeOfPostByPostId(postId);
    }

    @Override
    public void deleteLikePost(LikePost likePost) {
        likePostRepository.delete(likePost);
    }
}
