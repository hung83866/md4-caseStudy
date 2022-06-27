package com.example.casestudyteam2.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

//    @Max(value = 1000 , message = "Value should be less then then equal to 1000")
    private String content;

    private String status;

    private String video;

    private String imageFile;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users userPost;

    private int likes = 0;



    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Post() {
    }

    public Post(Long idPost, String content, String status, String imageUrl, String imageFile, Users userPost, int likes, Set<Comment> comments) {
        this.idPost = idPost;
        this.content = content;
        this.status = status;
        this.video = imageUrl;
        this.imageFile = imageFile;
        this.userPost = userPost;
        this.likes = likes;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String imageUrl) {
        this.video = imageUrl;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Users getUserPost() {
        return userPost;
    }

    public void setUserPost(Users userPost) {
        this.userPost = userPost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
