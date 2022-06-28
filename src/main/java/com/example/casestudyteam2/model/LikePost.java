package com.example.casestudyteam2.model;


import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "likePost")
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePostId;

    @ManyToOne
    @JoinColumn(name="postID")
    private Post post;

    @ManyToOne
    @JoinColumn(name="userID")
    private Users users;

    public LikePost(Optional<Post> post, Users users) {
    }

    public LikePost(Long likePostId, Post post, Users users) {
        this.likePostId = likePostId;
        this.post = post;
        this.users = users;
    }

    public LikePost() {

    }

    public Long getLikePostId() {
        return likePostId;
    }

    public LikePost(Post post, Users users) {
        this.post = post;
        this.users = users;
    }

    public void setLikePostId(Long likePostId) {
        this.likePostId = likePostId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
