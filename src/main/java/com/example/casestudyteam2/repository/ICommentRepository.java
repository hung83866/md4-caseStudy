package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findAllByPost(Post post);
}
