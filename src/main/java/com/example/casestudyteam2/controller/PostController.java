package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.service.IPostService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<Iterable<Post>> findAllPost() {
        Iterable<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/{iduser}")
    public ResponseEntity<Post> savePost(@RequestBody Post post, @PathVariable Long iduser) {
        post.setUserPost(userService.findById(iduser));
        post.setTime(new Date() +"");
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        postService.remove(post.get().getIdPost());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        post.setIdPost(postOptional.get().getIdPost());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> findByContent(@RequestParam String content) {
        Iterable<Post> posts = postService.findAllByContentContaining(content);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Optional<Post> postOptional = postService.findById(id);
        return new ResponseEntity<>(postOptional.get(),HttpStatus.OK);
    }
}
