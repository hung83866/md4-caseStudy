package com.example.casestudyteam2.model;


import javax.persistence.*;

@Entity
@Table(name = "likePost")
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Users users;

    public LikePost() {
    }

    public LikePost(Long idLike, Post post, Users users) {
        this.idLike = idLike;
        this.post = post;
        this.users = users;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
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
