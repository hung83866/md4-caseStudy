package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.Notice;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;

import java.util.Optional;

public interface INoticeService {
    Iterable<Notice> findAll();

    Optional<Notice> findById(Long id);

    void save(Notice post);

    void remove(Long id);
    void remove();

    Iterable<Notice> findAllByUserTo(Users users);
}
