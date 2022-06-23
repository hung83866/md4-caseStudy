package com.example.casestudyteam2.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    @ManyToOne
    @JoinColumn(name = "id_user_send")
    private Users userSend;

    @ManyToOne
    @JoinColumn(name = "id_user_receive")
    private Users userReceive;

    private String content;

    public Message() {
    }

    public Message(Long idMessage, Users userSend, Users userReceive, String content) {
        this.idMessage = idMessage;
        this.userSend = userSend;
        this.userReceive = userReceive;
        this.content = content;
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public Users getUserSend() {
        return userSend;
    }

    public void setUserSend(Users userSend) {
        this.userSend = userSend;
    }

    public Users getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(Users userReceive) {
        this.userReceive = userReceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
