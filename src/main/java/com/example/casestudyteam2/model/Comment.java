package com.example.casestudyteam2.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;
//    @Min(value = 0 , message = "Value should be greater then then equal to 4")
    @Max(value = 1000 , message = "Value should be less then then equal to 1000")
    private String content;

    @ManyToOne
    private Users user;

    public Comment() {
    }

    public Comment(Long idComment, String content, Users user) {
        this.idComment = idComment;
        this.content = content;
        this.user = user;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
