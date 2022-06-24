package com.example.casestudyteam2.dto.request;

public class ChangeAvatarForm {
    private String avatar;

    public ChangeAvatarForm() {
    }

    public ChangeAvatarForm(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

