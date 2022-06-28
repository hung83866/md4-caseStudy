package com.example.casestudyteam2.repository;

import com.example.casestudyteam2.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoticeRepository extends JpaRepository<Notice,Long> {
    Iterable<Notice> findAllByUsersTo(Long idUser);
}
