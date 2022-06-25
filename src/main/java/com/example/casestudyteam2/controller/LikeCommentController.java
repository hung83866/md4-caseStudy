package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.*;
import com.example.casestudyteam2.service.ICommentService;
import com.example.casestudyteam2.service.ILikeCommentService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/like-comment")
public class LikeCommentController {
    @Autowired
    ILikeCommentService likeCommentService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IUserService userService;

    //    Xem số lượng like của comment:
    @GetMapping("/get-like-comment-by-comment-id/{commentId}")
    public ResponseEntity<Integer> getLikeOfCommentByPostId(@PathVariable("commentId") Long commentId) {
        Integer noOfLikes = likeCommentService.countLikeOfCommentByCommentId(commentId);
        return new ResponseEntity<>(noOfLikes, HttpStatus.OK);
    }

//    Hủy Like Comment
    @GetMapping("/delete-like-comment/{commentId}/{userId}")
    public ResponseEntity<List<LikeComment>> deletelikeComment(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        List<LikeComment> likeComments = likeCommentService.findLikeCommentByCommentIdAndUserId(commentId, userId);
        if (likeComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < likeComments.size(); i++) {
                likeCommentService.deleteLikeComment(likeComments.get(i));
            }
            return new ResponseEntity<>(likeComments, HttpStatus.OK);
        }
    }

//    Like Comment:
    @GetMapping("/{commentId}/{userId}")
    public ResponseEntity<LikeComment> likeComment(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        Comment comment = commentService.findById(commentId);
        Users users = userService.findById(userId);
        LikeComment likeComment = new LikeComment(comment,users);
        likeCommentService.save(likeComment);
        return new ResponseEntity<>(likeComment, HttpStatus.OK);
    }
}
