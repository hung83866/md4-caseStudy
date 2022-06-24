package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;

import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();

    Optional<Post> findById(Long id);

    void save(Post post);

    void remove(Long id);

    Iterable<Post> findAllByContentContaining(String content);

    Iterable<Post> findAllByUserPost(Users users);

//    Iterable<Post> findAllByUserPostIdUser(Long id);
//
//    Iterable<Post> findAllByContentContaining(String hashtag);
//
//    Iterable<Post> findAllByUserPostIdUserOrStatus(Long id, String status);
//
//    Iterable<Post> findAllByUserPostAndStatus(Users user, String status);
}
