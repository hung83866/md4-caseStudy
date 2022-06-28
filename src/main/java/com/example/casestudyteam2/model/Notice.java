package com.example.casestudyteam2.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notice;
    @ManyToOne
    @JoinColumn(name = "users_from")
    private Users usersFrom;
    @ManyToOne
    @JoinColumn(name = "users_to")
    private Users usersTo;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String time;



    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Notice() {
    }

    public Notice(Long id, String notice, Users usersFrom, Users usersTo, boolean status, Post post, String time) {
        this.id = id;
        this.notice = notice;
        this.usersFrom = usersFrom;
        this.usersTo = usersTo;
        this.status = status;
        this.post = post;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Notice(String notice, Users usersFrom, Users usersTo,Post post, String time) {
        this.notice = notice;
        this.usersFrom = usersFrom;
        this.usersTo = usersTo;
        this.status = true;
        this.post = post;
        this.time =time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Users getUsersFrom() {
        return usersFrom;
    }

    public void setUsersFrom(Users usersFrom) {
        this.usersFrom = usersFrom;
    }

    public Users getUsersTo() {
        return usersTo;
    }

    public void setUsersTo(Users usersTo) {
        this.usersTo = usersTo;
    }
}
