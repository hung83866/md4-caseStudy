package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.Notice;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.INoticeService;
import com.example.casestudyteam2.service.IPostService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService noticeService;

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;
    //list thong báo theo id
    @GetMapping("/{idUser}")
    public ResponseEntity<Iterable<Notice>> findAllPostByUser(@PathVariable Long idUser) {
        Users users = userService.findById(idUser);
        Iterable<Notice> notices = noticeService.findAllByUserTo(users);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    // tạo thông báo
    @GetMapping("/save/{idUser}/{idPost}")
    public ResponseEntity<Iterable<?>> saveNotice(@PathVariable Long idUser,@PathVariable Long idPost) {
        Users usersFrom = userService.findById(idUser);
        Optional<Post> posts = postService.findById(idPost);
        Post post = new Post(posts.get().getIdPost(),posts.get().getContent(),posts.get().getStatus(),posts.get().getImageFile(),posts.get().getImageFile(),posts.get().getUserPost(),posts.get().getLikes());
        String notices = usersFrom.getName() + " commented on the post ";
        String time = new Date()+"";
        if (idUser==posts.get().getUserPost().getId()){
            return new ResponseEntity<>( HttpStatus.OK);
        }else {
            Notice notice = new Notice(notices,usersFrom,posts.get().getUserPost(),post,time);
            noticeService.save(notice);
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }
    @GetMapping("/save/like/{idUser}/{idPost}")
    public ResponseEntity<Iterable<?>> saveNotice1(@PathVariable Long idUser,@PathVariable Long idPost) {
        Users usersFrom = userService.findById(idUser);

        Optional<Post> posts = postService.findById(idPost);
        posts.get().setLikes(posts.get().getLikes()+1);
        Post post = new Post(posts.get().getIdPost(),posts.get().getContent(),posts.get().getStatus(),posts.get().getImageFile(),posts.get().getImageFile(),posts.get().getUserPost(),posts.get().getLikes());
        postService.save(post);
        String notices = usersFrom.getName() + " Liked on the post ";
        String time = new Date()+"";
        if (idUser==posts.get().getUserPost().getId()){
            return new ResponseEntity<>( HttpStatus.OK);
        }else {
            Notice notice = new Notice(notices,usersFrom,posts.get().getUserPost(),post,time);
            noticeService.save(notice);
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

    @GetMapping("/hide/{id}")
    public ResponseEntity<Iterable<?>> hideNotice(@PathVariable Long id){
        Optional<Notice> notice = noticeService.findById(id);
        notice.get().setStatus(false);
        Notice notice1 = new Notice(notice.get().getId(),notice.get().getNotice(),notice.get().getUsersFrom(),notice.get().getUsersTo(),notice.get().isStatus(),notice.get().getPost(),notice.get().getTime());
        noticeService.save(notice1);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
