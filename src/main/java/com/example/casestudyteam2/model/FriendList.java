package com.example.casestudyteam2.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "friendList")
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFriendList;

    @ManyToOne
    private Users userFrom;

    @ManyToOne
    private Users userTo;

    @Value("pending")
    private String status;

    public FriendList() {
    }

    public FriendList(Long idFriendList, Users userFrom, Users userTo, String status) {
        this.idFriendList = idFriendList;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
    }

    public FriendList(Long idFriendList, Users userFrom, Users userTo) {
        this.idFriendList = idFriendList;
        this.userFrom = userFrom;
        this.userTo = userTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdFriendList() {
        return idFriendList;
    }

    public void setIdFriendList(Long idFriendList) {
        this.idFriendList = idFriendList;
    }

    public Users getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Users userFrom) {
        this.userFrom = userFrom;
    }

    public Users getUserTo() {
        return userTo;
    }

    public void setUserTo(Users userTo) {
        this.userTo = userTo;
    }
}
