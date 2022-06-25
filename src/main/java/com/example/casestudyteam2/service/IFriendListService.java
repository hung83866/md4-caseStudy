package com.example.casestudyteam2.service;

import com.example.casestudyteam2.model.FriendList;

import java.util.Optional;

public interface IFriendListService {
    Iterable<FriendList> findAll();

    Optional<FriendList> findById(Long id);

    FriendList save(FriendList friendList);

    void sendFriendRequest(FriendList friendList);

    void acceptFriend(Long userFromId, Long userToId);

    void blockFriend(Long idUserFrom, Long idUserTo);

    String checkFriendStatus(Long userFirstId, Long userSecondId);

    Iterable<FriendList> findFriendListByIdUser(Long idUser);

    String checkFriendsStatus(Long idUserFrom, Long idUserTo);

    Iterable<FriendList> findAllPendingByIdUser(Long idUser);

    void deleteByUserFrom_IdAndUserTo_Id(Long idUserFrom, Long idUserTo);

    void addFriend(Long idUserFrom, Long idUserTo);
}
