package com.example.casestudyteam2.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private String image;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }


    public JwtResponse(Long id, String token, String name, String avatar,String image, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.avatar = avatar;
        this.image = image;
        this.roles = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
