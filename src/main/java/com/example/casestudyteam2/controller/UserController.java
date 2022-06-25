package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

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

    @GetMapping("/enable/{id}")
    public ResponseEntity<Users> enableUser(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        user.setEnabled(true);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
