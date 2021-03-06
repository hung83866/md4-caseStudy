package com.example.casestudyteam2.security.userpincal;

import com.example.casestudyteam2.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private Date birthday;
    private String avatar;
    private String image;
    private String address;
    private String sex;

    public UserPrinciple(Long id, String name, String username, String email, String password, String phone, Date birthday, String avatar, String image, String address, String interests,String sex, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.avatar = avatar;
        this.image = image;
        this.address = address;
        this.interests = interests;
        this.sex = sex;
        this.roles = roles;
    }

    private String interests;
    private Collection<? extends GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public UserPrinciple(Long id, String name, String username, String email, String password, String phone, String birthday, String avatar, List<GrantedAuthority> authorities) {
    }

    public UserPrinciple(Long id, String name, String username, String email, String password,String phone,Date birthday, String avatar,String image, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.image = image;
        this.phone= phone;
        this.birthday= birthday;
        this.roles = roles;
    }

    public static UserPrinciple build(Users users) {
        List<GrantedAuthority> authorities = users.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(
                users.getId(),
                users.getName(),
                users.getUsername(),
                users.getEmail(),
                users.getPassword(),
                users.getPhone(),
                users.getBirthday(),
                users.getAvatar(),
                users.getImage(),
                users.getAddress(),
                users.getInterests(),
                users.getSex(),
                authorities
        );
    }

    public String getAvatar() {
        return avatar;
    }
    public String getImage() {
        return image;
    }


    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
