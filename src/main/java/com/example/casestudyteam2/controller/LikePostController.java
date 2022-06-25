package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.LikePost;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.ILikePostService;
import com.example.casestudyteam2.service.IPostService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/like-post")
public class LikePostController {
    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;


//    @GetMapping("/get_all_like_post")
//    public ResponseEntity<List<LikePost>> showAllLikePosts() {
//        List<LikePost> likePosts = likePostService.findAll();
//        if (likePosts.isEmpty()) {
//            new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(likePosts, HttpStatus.OK);
//    }

//    @GetMapping("/get_like_post-by/{postId}/{userId}")
//    public ResponseEntity<LikePost> showLikePostBy(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
//        LikePost likePost = likePostService.findLikePostByPostIdAndUserId(postId, userId);
//        return new ResponseEntity<>(likePost, HttpStatus.OK);
//    }

    //    Xem số lượng like của post:
    @GetMapping("/get-like-post-by-post-id/{postId}")
    public ResponseEntity<Integer> getLikeOfPostByPostId(@PathVariable("postId") Long postId) {
        Integer noOfLikes = likePostService.countLikeOfPostByPostId(postId);
        return new ResponseEntity<>(noOfLikes, HttpStatus.OK);
    }

//    Hủy like post:
    @GetMapping("/delete-like-post/{postId}/{userId}")
    public ResponseEntity<List<LikePost>> deleteLikePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        List<LikePost> likePosts = likePostService.findLikePostByPostIdAndUserId(postId, userId);
        if (likePosts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < likePosts.size(); i++) {
                likePostService.deleteLikePost(likePosts.get(i));
            }
            return new ResponseEntity<>(likePosts, HttpStatus.OK);
        }
    }

//    Like Post:
    @GetMapping("/{postId}/{userId}")
    public ResponseEntity<LikePost> likePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        Post post = postService.findById(postId).get();
        Users users = userService.findById(userId);
        post.setLikes(post.getLikes()+1);
        LikePost likePost = new LikePost(post,users);
        likePostService.save(likePost);
        return new ResponseEntity<>(likePost, HttpStatus.OK);
    }
}
