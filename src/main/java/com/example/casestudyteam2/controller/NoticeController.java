package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.Notice;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.INoticeService;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService noticeService;

    @Autowired
    IUserService userService;

    //list thong báo theo id
    @GetMapping("/{idUser}")
    public ResponseEntity<Iterable<Notice>> findAllPostByUser(@PathVariable Long idUser) {
        Users users = userService.findById(idUser);
        Iterable<Notice> notices = noticeService.findAllByUserTo(users);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }
}
