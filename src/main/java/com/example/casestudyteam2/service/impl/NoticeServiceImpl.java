package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.Notice;
import com.example.casestudyteam2.model.Post;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.repository.INoticeRepository;
import com.example.casestudyteam2.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    INoticeRepository noticeRepository;

    @Override
    public Iterable<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }

    @Override
    public void save(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public void remove(Long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public void remove() {
        noticeRepository.deleteAll();
    }

    @Override
    public Iterable<Notice> findAllByUserTo(Users users) {
        return noticeRepository.findAllByUsersTo(users);
    }
}
