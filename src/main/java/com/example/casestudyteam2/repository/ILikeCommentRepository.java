package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.LikeComment;
import com.example.casestudyteam2.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    @Query(value = "select count(like_comment_id) from like_comment group by comment_id = :commentId", nativeQuery = true)
    Integer countLikeOfCommentByCommentId(Long commentId);
    @Query(value = "select * from like_comment where comment_id = :commentId and user_id = :userId", nativeQuery = true)
    List<LikeComment> findLikeCommentByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
