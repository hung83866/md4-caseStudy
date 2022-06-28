package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.Notice;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/notice")
public class NocateController {
    @Autowired
    INoticeService noticeService;

    //list thong báo theo id
    @GetMapping("/{idUser}")
    public ResponseEntity<Iterable<Notice>> findAllPostByUser(@PathVariable Long idUser){
        Iterable<Notice> notices = noticeService.findAllByUserTo(idUser);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }
}
