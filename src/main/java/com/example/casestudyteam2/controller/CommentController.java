package com.example.casestudyteam2.controller;


import com.example.casestudyteam2.dto.request.CommentForm;
import com.example.casestudyteam2.model.Comment;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.ICommentService;
import com.example.casestudyteam2.service.IPostService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<Iterable<Comment>> showComment() {
        Iterable<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> createNewComment(@RequestBody CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setUser(userService.findById(commentForm.getIdUser()));
        comment.setPost(postService.findById(commentForm.getIdPost()).get());
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}




