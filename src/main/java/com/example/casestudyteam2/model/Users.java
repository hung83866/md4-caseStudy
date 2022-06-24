package com.example.casestudyteam2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 50)
    private String name;
    @Size(min = 3,max = 50)
    private String username;
    @Size(max = 50)
    @Email
    private String email;
    @JsonIgnore
    @Size(min = 6,max = 100)
    private String password;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Lob
    private String avatar;
    @Lob
    private String image;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Users() {

    }

    public Users(Long id, String name, String username, String email, String password, String phone, Date birthday, String avatar,String image, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled=true;
        this.phone = phone;
        this.birthday = birthday;
        this.avatar = avatar;
        this.image = image;
        this.roles = roles;
    }

    public Users(@Size(min = 3, max = 50) String name,
                 @Size(min = 3,max = 50) String username,
                 @Size(max = 50)
                 @Email String email,
                 @Size(min = 6,max = 100)String encode,
                 String phone,
                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                 Date birthday,
                 String avatar) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
        this.phone= phone;
        this.enabled=true;
        this.birthday= birthday;
        this.avatar = avatar;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
