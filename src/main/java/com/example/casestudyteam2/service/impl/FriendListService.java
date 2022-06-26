package com.example.casestudyteam2.service.impl;

import com.example.casestudyteam2.model.FriendList;
import com.example.casestudyteam2.repository.IFriendListRepository;
import com.example.casestudyteam2.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendListService implements IFriendListService {

    @Autowired
    private IFriendListRepository friendListRepository;

    @Override
    public Iterable<FriendList> findAll() {
        return friendListRepository.findAll();
    }

    @Override
    public Optional<FriendList> findById(Long id) {
        if (friendListRepository.findById(id).isPresent()) {
            return friendListRepository.findById(id);
        }
        return null;
    }

    @Override
    public FriendList save(FriendList friendList) {
        String status = checkFriendStatus(friendList.getUserFrom().getId(), friendList.getUserTo().getId());
        if (status == null){
            friendListRepository.save(friendList);
        }
        return null;
    }

    @Override
    public void sendFriendRequest(FriendList friendList) {
        String status = checkFriendStatus(friendList.getUserFrom().getId(), friendList.getUserTo().getId());
        if (status == null){
            friendListRepository.save(friendList);
        }
    }

    @Override
    public void acceptFriend(Long userFromId, Long userToId) {
        String status1 = checkFriendStatus(userFromId, userToId);
        String status2 = checkFriendStatus(userToId, userFromId);
        if (checkFriendStatus(userFromId, userToId) == null){
            status1 = "";
        }
        if (checkFriendStatus(userToId, userFromId) == null){
            status2 = "";
        }

        if (status1.equals("pending")){
            friendListRepository.acceptFriendRequest(userFromId, userToId);
        }else if (status2.equals("pending")){
            friendListRepository.acceptFriendRequest(userToId, userFromId);
        }
    }

    @Override
    public void blockFriend(Long idUserFrom, Long idUserTo) {
        String status1 = checkFriendStatus(idUserFrom, idUserTo);
        String status2 = checkFriendStatus(idUserTo, idUserFrom);
        if (status2 != null){
            friendListRepository.blockFriendRequest(idUserTo, idUserFrom);
        }else if (status1 != null){
            friendListRepository.blockFriendRequest(idUserFrom, idUserTo);
        }
    }

    @Override
    public String checkFriendStatus(Long userFirstId, Long userSecondId) {
        String status = null;
        Iterable<FriendList> friendLists = friendListRepository.findAll();
        for (FriendList friendList : friendLists){
            if ((friendList.getUserFrom().getId() == userFirstId && friendList.getUserTo().getId() == userSecondId)){
                status = friendList.getStatus();
            }
        }
        return status;
    }

    @Override
    public Iterable<FriendList> findFriendListByIdUser(Long idUser) {
        return friendListRepository.findFriendListByIdUser(idUser);
    }

    @Override
    public String checkFriendsStatus(Long idUserFrom, Long idUserTo) {
        String status = "non friend";
        String status1 = friendListRepository.checkFriendsStatus(idUserFrom, idUserTo);
        String status2 = friendListRepository.checkFriendsStatus(idUserTo, idUserFrom);
        if (friendListRepository.checkFriendsStatus(idUserFrom, idUserTo) == null){
            status1 = "";
        }

        if (friendListRepository.checkFriendsStatus(idUserTo, idUserFrom) == null){
            status2 = "";
        }

        if ((status1.equals("friend")) || (status2.equals("friend"))){
            status = "friend";
        }else if (status1.equals("pending")){
            status = "pending";
        }else if(status2.equals("pending")){
            status = "respond";
        }else if (status1.equals("block")){
            status = "block";
        }else if (status2.equals("block")){
            status = "blocked";
        }
        return status;
    }

    @Override
    public Iterable<FriendList> findAllPendingByIdUser(Long idUser) {
        return friendListRepository.findAllPendingByIdUser(idUser);
    }

    @Override
    public void deleteByUserFrom_IdAndUserTo_Id(Long idUserFrom, Long idUserTo) {
        if (checkFriendStatus(idUserFrom, idUserTo)==null){
            friendListRepository.deleteByUserFrom_IdAndUserTo_Id(idUserTo, idUserFrom);
        }else {
            friendListRepository.deleteByUserFrom_IdAndUserTo_Id(idUserFrom, idUserTo);
        }
    }

    @Override
    public void addFriend(Long idUserFrom, Long idUserTo) {
        friendListRepository.addFriend(idUserFrom, idUserTo);
    }
}
