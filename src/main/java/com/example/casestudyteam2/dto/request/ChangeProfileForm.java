package com.example.casestudyteam2.dto.request;

import java.util.Date;

public class ChangeProfileForm {
    private String name;
    private String username;
    private String email;
    private String phone;
    private Date birthday;


    public ChangeProfileForm() {
    }

    public ChangeProfileForm(String name, String username, String email,String phone,Date birthday) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone= phone;
        this.birthday= birthday;
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
}
