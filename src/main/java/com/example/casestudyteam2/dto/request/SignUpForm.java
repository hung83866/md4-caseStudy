package com.example.casestudyteam2.dto.request;

import java.util.Date;
import java.util.Set;

public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Date birthday;
    private String avatar;
    private String image;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String name, String username, String email, String password, String phone, Date birthday, String avatar, Set<String> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone= phone;
        this.birthday=birthday;
        this.avatar = avatar;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
