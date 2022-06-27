package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.dto.request.ChangeAvatarForm;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.IPostService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;
//tìm post theo người viết
    @GetMapping("/view/{idUser}")
    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long idUser){
        Optional<Users> usersOptional = Optional.ofNullable(userService.findById(idUser));
        Iterable<Post> posts = postService.findAllByUserPost(usersOptional.get());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
//List User
    @GetMapping("/list")
    public ResponseEntity<Iterable<Users>> showAllUsers() {
        List<Users> users = userService.findAll();
        if (users.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
//vo hiệu hóa tk
    @GetMapping("/disable/{id}")
    public ResponseEntity<Users> disableUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        user.setEnabled(false);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
//mơ lại tk
    @GetMapping("/enable/{id}")
    public ResponseEntity<Users> enableUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        user.setEnabled(true);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

// tìm user theo id
    @GetMapping("/{id}")
    public ResponseEntity<Users> findByUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
// Update user
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users,@PathVariable("id") Long id) {
        Users userOptional = userService.findById(id);
        users.setId(userOptional.getId());
        users.setAvatar(userOptional.getAvatar());
        users.setImage(userOptional.getImage());
        users.setUsername(userOptional.getUsername());
        users.setPassword(userOptional.getPassword());
        userService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

// Update Avatar
    @PutMapping("/avatar/{id}")
    public ResponseEntity<Users> updateAvatar(@RequestBody ChangeAvatarForm changeAvatarForm, @PathVariable("id") Long id) {
        Users userOptional = userService.findById(id);
        Users users = new Users(userOptional.getId(),userOptional.getName(),userOptional.getUsername(),userOptional.getEmail(),
                userOptional.getPassword(),userOptional.getPhone(),userOptional.getBirthday(),changeAvatarForm.getAvatar(),
                userOptional.getImage(),userOptional.getAddress(),userOptional.getInterests(),userOptional.getRoles(),userOptional.getSex());
        userService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

// Update ảnh bìa
    @PutMapping("/image/{id}")
    public ResponseEntity<Users> updateImage(@RequestBody ChangeAvatarForm changeAvatarForm, @PathVariable("id") Long id) {
        Users userOptional = userService.findById(id);
        Users users = new Users(userOptional.getId(),userOptional.getName(),userOptional.getUsername(),userOptional.getEmail(),
                userOptional.getPassword(),userOptional.getPhone(),userOptional.getBirthday(),userOptional.getAvatar(),
                changeAvatarForm.getAvatar(),userOptional.getAddress(),userOptional.getInterests(),userOptional.getRoles(),userOptional.getSex());
        userService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//tim user theo name
    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<Users>> searchUsername(@PathVariable String name){
        List<Users> users = userService.findByNameCon(name);
        if (users.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
