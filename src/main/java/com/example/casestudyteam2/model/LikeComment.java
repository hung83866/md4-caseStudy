package com.example.casestudyteam2.model;

import javax.persistence.*;

@Entity
@Table(name = "likeComment")
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LikeCommentId;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userLike;

    public LikeComment(Comment comment, Users users) {
    }

    public LikeComment(Long likeCommentId, Comment comment, Users userLike) {
        LikeCommentId = likeCommentId;
        this.comment = comment;
        this.userLike = userLike;
    }

    public LikeComment() {
    }



    public Long getLikeCommentId() {
        return LikeCommentId;
    }

    public void setLikeCommentId(Long likeCommentId) {
        LikeCommentId = likeCommentId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Users getUserLike() {
        return userLike;
    }

    public void setUserLike(Users userLike) {
        this.userLike = userLike;
    }
}
