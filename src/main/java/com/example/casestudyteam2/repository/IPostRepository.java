package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByContentContaining(String content);
//    Iterable<Post> findAllPostByUser(Users users);
    Iterable<Post> findAllByUserPost(Users users);

}
