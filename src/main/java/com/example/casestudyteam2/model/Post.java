package com.example.casestudyteam2.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @Max(value = 1000 , message = "Value should be less then then equal to 1000")
    private String content;

    @Max(value = 1000 , message = "Value should be less then then equal to 1000")
    private String status;

    private String imageUrl;

    @Transient
    private MultipartFile imageFile;

    @ManyToOne
    private Users userPost;

    @OneToMany(targetEntity = LikePost.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_fkl")
    private Set<LikePost> likes;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_fk")
    private Set<Comment> comments;

    public Post() {
    }

    public Post(Long idPost, String content, String status, String imageUrl, MultipartFile imageFile, Users userPost, Set<LikePost> likes, Set<Comment> comments) {
        this.idPost = idPost;
        this.content = content;
        this.status = status;
        this.imageUrl = imageUrl;
        this.imageFile = imageFile;
        this.userPost = userPost;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Users getUserPost() {
        return userPost;
    }

    public void setUserPost(Users userPost) {
        this.userPost = userPost;
    }

    public Set<LikePost> getLikes() {
        return likes;
    }

    public void setLikes(Set<LikePost> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
