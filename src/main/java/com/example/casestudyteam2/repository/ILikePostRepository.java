package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.LikeComment;
import com.example.casestudyteam2.model.LikePost;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {

    @Query(value = "select count(like_post_id) from like_post group by postid = :postId", nativeQuery = true)
    Integer countLikeOfPostByPostId(Long postId);
    @Query(value = "select * from like_post where post_id = :postId and user_id = :userId", nativeQuery = true)
    List<LikePost> findLikePostByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}
