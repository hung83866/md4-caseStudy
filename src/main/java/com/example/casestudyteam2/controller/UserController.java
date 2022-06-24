package com.example.casestudyteam2.controller;

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

    @GetMapping("/view/{idUser}")
    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long idUser){
        Optional<Users> usersOptional = Optional.ofNullable(userService.findById(idUser));
        Iterable<Post> posts = postService.findAllByUserPost(usersOptional.get());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Users>> showAllUsers() {
        List<Users> users = userService.findAll();
        if (users.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/disable/{id}")
    public ResponseEntity<Users> disableUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        user.setEnabled(false);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Users> findByUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
